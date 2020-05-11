package tk.aizydorczyk.sns.operation.domain.support;

import org.springframework.data.repository.CrudRepository;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntityListener;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

public class HashMapRepository<EntityType extends BaseEntity> implements CrudRepository<EntityType, Long> {
    private final Map<Long, EntityType> entitiesMap;

    private long idSequence = 0;

    public HashMapRepository(List<EntityType> entities,
                             AuditingInformation auditingInformation) {
        this.entitiesMap = new HashMap<>();
        entities.stream()
                .peek(entity -> entity.applyAuditingInformation(auditingInformation))
                .forEach(this::save);
    }

    public HashMapRepository() {
        this.entitiesMap = new HashMap<>();
    }

    @Override
    public List<EntityType> findAll() {
        return new ArrayList<>(entitiesMap.values());
    }

    @Override
    public List<EntityType> findAllById(Iterable<Long> ids) {
        return StreamSupport.stream(ids.spliterator(), false)
                .map(entitiesMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return entitiesMap.values().size();
    }

    @Override
    public void deleteById(Long id) {
        entitiesMap.values()
                .removeIf(entity -> entity.getId().equals(id));
    }

    @Override
    public void delete(EntityType entity) {
        final EntityType entityToDelete = entitiesMap.get(entity.getId());
        final Field deleteField = fetchFieldFromEntity("deleted");
        deleteField.setAccessible(true);
        assignValueToEntityField(entityToDelete, deleteField, true);
    }

    @Override
    public void deleteAll(Iterable<? extends EntityType> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        entitiesMap.clear();
    }

    @Override
    public <S extends EntityType> S save(S entity) {
            final BaseEntityListener entityListener = new BaseEntityListener();
            if (isNull(entity.getId())) {
                final Field idField = fetchFieldFromEntity("id");
                idField.setAccessible(true);
                final long newId = ++idSequence;
                assignValueToEntityField(entity, idField, newId);
                entityListener.preCreate(entity);
                return (S) entitiesMap.put(newId, entity);
            } else {
                entityListener.preUpdate(entity);
                return (S) entitiesMap.put(entity.getId(), entity);
            }
    }

    @Override
    public <S extends EntityType> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .peek(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EntityType> findById(Long id) {
        return Optional.ofNullable(entitiesMap.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return entitiesMap.containsKey(id);
    }

    private void assignValueToEntityField(EntityType entity,
                                          Field field,
                                          Object value) {
        try {
            field.set(entity, value);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Field fetchFieldFromEntity(String fieldName) {
        try {
            return BaseEntity.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package tk.aizydorczyk.sns.operation.domain.support;

import org.springframework.data.repository.CrudRepository;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntityListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

public class HashMapRepository<EntityType extends BaseEntity> implements CrudRepository<EntityType, Long> {
    private final Map<Long, EntityType> entitiesMap;

    private long idSequence = 0;

    public HashMapRepository(List<EntityType> entities) {
        this.entitiesMap = entities.stream()
                .peek(entityType -> ++idSequence)
                .collect(Collectors.toMap(BaseEntity::getId, entity -> entity));
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
        entitiesMap.values().removeIf(entity -> entity.getId().equals(id));
    }

    @Override
    public void delete(EntityType entity) {
        entitiesMap.values().remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends EntityType> entities) {
        entities.forEach(entitiesMap.values()::remove);
    }

    @Override
    public void deleteAll() {
        entitiesMap.clear();
    }

    @Override
    public <S extends EntityType> S save(S entity) {
        try {
            final BaseEntityListener entityListener = new BaseEntityListener();
            if (isNull(entity.getId())) {
                final Field idField = BaseEntity.class.getDeclaredField("id");
                idField.setAccessible(true);
                final long newId = ++idSequence;
                idField.set(entity, newId);
                entityListener.preCreate(entity);
                return (S) entitiesMap.put(newId, entity);
            } else {
                entityListener.preUpdate(entity);
                return (S) entitiesMap.put(entity.getId(), entity);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
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
}

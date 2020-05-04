package tk.aizydorczyk.sns.search.infrastructure.query;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicJpqlQueryGenerator<SearchEntityType> {

    private final List<Field> allEntityFields;
    private final Class<SearchEntityType> searchEntityTypeClass;
    private final EntityManager entityManager;
    private final Mapper mapper;

    public DynamicJpqlQueryGenerator(EntityManager entityManager,
                                     Class<SearchEntityType> searchEntityTypeClass,
                                     Mapper mapper) {
        this.searchEntityTypeClass = searchEntityTypeClass;
        this.allEntityFields = fetchAllFields(searchEntityTypeClass);
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    private List<Field> fetchAllFields(Class<SearchEntityType> searchEntityTypeClass) {
        final List<Field> fields = new ArrayList<>(List.of(searchEntityTypeClass.getDeclaredFields()));
        fetchSuperClassFields(searchEntityTypeClass.getSuperclass(), fields);
        return fields;
    }

    private void fetchSuperClassFields(Class<? super SearchEntityType> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }

    public List<SearchEntityType> findEntities(QueryFilter[] filters) {
        final String whereClause = Arrays.stream(filters)
                .map(QueryFilter::getField)
                .map(fieldName -> String.format("e.%s = :%s", fieldName, fieldName))
                .collect(Collectors.joining(" and "));

        final TypedQuery<SearchEntityType> query = entityManager.createQuery(
                String.format("select e from %s e where %s", searchEntityTypeClass.getCanonicalName(), whereClause),
                searchEntityTypeClass);
        setParameters(filters, query);
        return query.getResultList();
    }

    private void setParameters(QueryFilter[] filters, Query query) {
        for (QueryFilter filter : filters) {
            try {
                final Class<?> entityFieldType = allEntityFields.stream()
                        .filter(field -> field.getName().equals(filter.getField()))
                        .findFirst()
                        .map(Field::getType)
                        .orElseThrow();
                query.setParameter(filter.getField(), mapper.map(filter.getValue(), entityFieldType));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}

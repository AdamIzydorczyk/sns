package tk.aizydorczyk.sns.search.infrastructure.query;

import org.apache.commons.lang3.tuple.Pair;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.utils.ClassUtils;
import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicJpqlQueryGenerator<SearchEntityType> {

    private final Class<SearchEntityType> searchEntityTypeClass;
    private final EntityManager entityManager;
    private final Mapper mapper;
    private final Map<? extends Class<?>, Map<String, Field>> entitiesFields;

    public DynamicJpqlQueryGenerator(EntityManager entityManager,
                                     Class<SearchEntityType> searchEntityTypeClass,
                                     Mapper mapper) {
        this.searchEntityTypeClass = searchEntityTypeClass;
        this.entitiesFields = fetchAllFields(searchEntityTypeClass);
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    private Map<? extends Class<?>, Map<String, Field>> fetchAllFields(Class<SearchEntityType> searchEntityTypeClass) {
        return ClassUtils.findAssignableTypes("tk.aizydorczyk.sns.search", BaseSearchEntity.class).stream()
                .map(ClassUtils::loadClass)
                .map(entityClass -> {
                    final List<Field> fields = new ArrayList<>(List.of(entityClass.getDeclaredFields()));
                    fetchSuperClassFields(entityClass.getSuperclass(), fields);
                    return Pair.of(entityClass, fields);
                }).collect(Collectors.toMap(Pair::getLeft, classListPair -> classListPair.getRight().stream()
                        .collect(Collectors.toMap(Field::getName, field -> field))));
    }

    private void fetchSuperClassFields(Class<?> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }

    public List<SearchEntityType> findEntities(QueryFilter[] filters) {
        int joinIndex = 0;
        final List<String> joins = new ArrayList<>();
        final List<String> ands = new ArrayList<>();
        for (QueryFilter queryFilter : filters) {
            final String field = queryFilter.getField();
            final String[] splittedField = field.split("_");

            for (int index = 0; index < splittedField.length - 1; index++) {
                final String subField = splittedField[index];
                if (index == 0) {
                    joins.add("join e." + subField + " j" + (++joinIndex));
                } else {
                    joins.add("join j" + joinIndex + "." + subField + " j" + (++joinIndex));
                }
            }

            if (splittedField.length == 1) {
                ands.add("e." + queryFilter.getField() + " = :" + queryFilter.getField());
            } else {
                final String lastSubField = splittedField[splittedField.length - 1];
                ands.add("j" + joinIndex + "." + lastSubField + " = :" + queryFilter.getField());
            }
        }

        String whereClause;
        if (ands.size() > 0) {
            whereClause = "where " + String.join(" and ", ands);
        } else {
            whereClause = "";
        }

        final String join = String.join(" ", joins);
        final TypedQuery<SearchEntityType> query = entityManager.createQuery(
                String.format("select distinct e from %s e %s %s", searchEntityTypeClass.getCanonicalName(), join, whereClause),
                searchEntityTypeClass);
        setParameters(filters, query);
        return query.getResultList();
    }

    private void setParameters(QueryFilter[] filters, Query query) {
        for (QueryFilter filter : filters) {
            try {
                final String[] splitFields = filter.getField().split("_");
                final LinkedList<String> fieldNamesQueue = new LinkedList<>(Arrays.asList(splitFields));
                Class<?> entityFieldType = searchEntityTypeClass;
                while (true) {
                    final String fieldName = fieldNamesQueue.pop();
                    final Field field = entitiesFields.get(entityFieldType).get(fieldName);
                    if (fieldNamesQueue.isEmpty()) {
                        final Field declaredField = entitiesFields.get(entityFieldType).get(fieldName);
                        query.setParameter(filter.getField(), mapper.map(filter.getValue(), declaredField.getType()));
                        break;
                    }
                    entityFieldType = getFieldType(field);
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getFieldType(Field field) {
        if (isCollection(field.getType())) {
            return getCollectionGenericType(field);
        } else {
            return field.getType();
        }
    }

    private boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    public Class<?> getCollectionGenericType(Field field) {
        final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }
}

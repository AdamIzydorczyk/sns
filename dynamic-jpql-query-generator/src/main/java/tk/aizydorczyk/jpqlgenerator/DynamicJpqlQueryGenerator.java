package tk.aizydorczyk.jpqlgenerator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class DynamicJpqlQueryGenerator<SearchEntityType> {
    private final Class<SearchEntityType> searchEntityTypeClass;
    private final EntityManager entityManager;
    private final BiFunction<Object, Class<?>, Object> mapper;
    private final Map<? extends Class<?>, Map<String, Field>> entitiesFields;

    public DynamicJpqlQueryGenerator(EntityManager entityManager,
                                     Class<SearchEntityType> searchEntityTypeClass,
                                     BiFunction<Object, Class<?>, Object> mapper,
                                     Supplier<? extends Collection<? extends Class<?>>> entityTypesProvider) {
        this.searchEntityTypeClass = searchEntityTypeClass;
        this.entitiesFields = fetchAllFields(entityTypesProvider);
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    private Map<? extends Class<?>, Map<String, Field>> fetchAllFields(Supplier<? extends Collection<? extends Class<?>>> entityTypesProvider) {
        return entityTypesProvider.get().stream()
                .map(EntityClassAndListOfFieldsPair::new)
                .collect(Collectors.toMap(
                        EntityClassAndListOfFieldsPair::getEntityClass,
                        classListPair -> classListPair.getFields().stream()
                                .collect(Collectors.toMap(Field::getName, field -> field))));
    }

    public List<SearchEntityType> findEntities(QueryFilter[] filters) {
        final List<QueryFilter> queryFilters = new LinkedList<>(Arrays.asList(filters));
        int joinIndex = 0;
        final List<String> joins = new ArrayList<>();
        final List<String> ands = new ArrayList<>();

        final Iterator<QueryFilter> queryFilterIterator = queryFilters.iterator();

        while (queryFilterIterator.hasNext()) {
            final QueryFilter queryFilter = queryFilterIterator.next();

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
                if (nonNull(queryFilter.getValue())) {
                    ands.add("e." + queryFilter.getField() + " = :" + queryFilter.getField());
                } else {
                    queryFilterIterator.remove();
                    ands.add("e." + queryFilter.getField() + " is null");
                }
            } else {
                final String lastSubField = splittedField[splittedField.length - 1];
                if (nonNull(queryFilter.getValue())) {
                    ands.add("j" + joinIndex + "." + lastSubField + " = :" + queryFilter.getField());
                } else {
                    queryFilterIterator.remove();
                    ands.add("j" + joinIndex + "." + lastSubField + " is null");
                }
            }
        }

        final String whereClause = prepareWhereClause(ands);

        final String join = String.join(" ", joins);
        final TypedQuery<SearchEntityType> query = entityManager.createQuery(
                String.format("select distinct e from %s e %s %s", searchEntityTypeClass.getCanonicalName(), join, whereClause),
                searchEntityTypeClass);
        setParametersToQuery(queryFilters, query);
        return query.getResultList();
    }

    private String prepareWhereClause(List<String> ands) {
        String whereClause;
        if (ands.size() > 0) {
            whereClause = "where " + String.join(" and ", ands);
        } else {
            whereClause = "";
        }
        return whereClause;
    }

    private void setParametersToQuery(List<QueryFilter> filters, Query query) {
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
                        query.setParameter(filter.getField(), mapper.apply(filter.getValue(), declaredField.getType()));
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

    private Class<?> getCollectionGenericType(Field field) {
        final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

}

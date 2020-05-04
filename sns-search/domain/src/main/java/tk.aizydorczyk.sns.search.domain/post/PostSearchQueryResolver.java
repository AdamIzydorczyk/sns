package tk.aizydorczyk.sns.search.domain.post;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
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

public class PostSearchQueryResolver implements GraphQLQueryResolver {

    private final EntityManager entityManager;

    private final Class<PostSearch> postSearchClass = PostSearch.class;
    private final Mapper mapper;

    PostSearchQueryResolver(Mapper mapper,
                            EntityManager entityManager) {
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    public List<PostSearch> findPosts(QueryFilter[] filters) {
        final String where = Arrays.stream(filters)
                .map(QueryFilter::getField)
                .map(fieldName -> "p." + fieldName + " = :" + fieldName)
                .collect(Collectors.joining(" and "));

        final TypedQuery<PostSearch> query = entityManager.createQuery("select p from " + postSearchClass.getCanonicalName() + " p where " + where, PostSearch.class);

        setParameters(filters, query);

        return query.getResultList();
    }

    private void setParameters(QueryFilter[] filters, Query query) {
        for (QueryFilter filter : filters) {
            try {
                final Class<?> entityFieldType = fetchAllFields().stream()
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

    private List<Field> fetchAllFields() {
        final List<Field> fields = new ArrayList<>(List.of(postSearchClass.getDeclaredFields()));
        fetchSuperClassFields(postSearchClass.getSuperclass(), fields);
        return fields;
    }

    private void fetchSuperClassFields(Class<? super PostSearch> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }
}

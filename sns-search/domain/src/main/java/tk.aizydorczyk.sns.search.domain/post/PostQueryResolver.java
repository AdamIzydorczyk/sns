package tk.aizydorczyk.sns.search.domain.post;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.sns.common.domain.post.Post;
import tk.aizydorczyk.sns.common.domain.post.PostDto;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PostQueryResolver implements GraphQLQueryResolver {

    private final Supplier<List<Post>> findAllPosts;
    private final EntityManager entityManager;

    private final Class<Post> postClass = Post.class;
    private final Mapper mapper;

    PostQueryResolver(Supplier<List<Post>> findAllPosts,
                      Mapper mapper,
                      EntityManager entityManager) {
        this.findAllPosts = findAllPosts;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    public List<PostDto> findPosts(QueryFilter[] filters) {
        final String where = Arrays.stream(filters)
                .map(QueryFilter::getField)
                .map(fieldName -> "p." + fieldName + " = :" + fieldName)
                .collect(Collectors.joining(" and "));

        final TypedQuery<Post> query = entityManager.createQuery("select p from " + postClass.getCanonicalName() + " p where " + where, Post.class);

        setParameters(filters, query);

        return query.getResultList().stream()
                .map(post -> mapper.map(post, PostDto.class))
                .collect(toList());
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
        final List<Field> fields = new ArrayList<>(List.of(postClass.getDeclaredFields()));
        fetchSuperClassFields(postClass.getSuperclass(), fields);
        return fields;
    }

    private void fetchSuperClassFields(Class<? super Post> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }
}

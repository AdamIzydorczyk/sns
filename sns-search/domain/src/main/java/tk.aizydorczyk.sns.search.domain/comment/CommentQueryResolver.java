package tk.aizydorczyk.sns.search.domain.comment;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.sns.common.domain.comment.Comment;
import tk.aizydorczyk.sns.common.domain.comment.CommentDto;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.search.domain.post.QueryFilter;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CommentQueryResolver implements GraphQLQueryResolver {
    private final EntityManager entityManager;

    private final Class<Comment> commentClass = Comment.class;
    private final Mapper mapper;

    CommentQueryResolver(Mapper mapper,
                         EntityManager entityManager) {
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    public List<CommentDto> findComments(QueryFilter[] filters) {
        final String where = Arrays.stream(filters)
                .map(QueryFilter::getField)
                .map(fieldName -> "e." + fieldName + " = :" + fieldName)
                .collect(Collectors.joining(" and "));

        final TypedQuery<Comment> query = entityManager.createQuery("select e from " + commentClass.getCanonicalName() + " e where " + where, Comment.class);

        setParameters(filters, query);

        return query.getResultList().stream()
                .map(post -> mapper.map(post, CommentDto.class))
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
        final List<Field> fields = new ArrayList<>(List.of(commentClass.getDeclaredFields()));
        fetchSuperClassFields(commentClass.getSuperclass(), fields);
        return fields;
    }

    private void fetchSuperClassFields(Class<? super Comment> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }
}

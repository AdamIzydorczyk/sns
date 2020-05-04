package tk.aizydorczyk.sns.search.domain.comment;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.sns.search.infrastructure.query.QueryFilter;

import java.util.List;
import java.util.function.Function;

public class CommentSearchQueryResolver implements GraphQLQueryResolver {
    private Function<QueryFilter[], List<CommentSearch>> findComments;

    CommentSearchQueryResolver(Function<QueryFilter[], List<CommentSearch>> findComments) {
        this.findComments = findComments;
    }

    public List<CommentSearch> findComments(QueryFilter[] filters) {
        return findComments.apply(filters);
    }
}

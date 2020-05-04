package tk.aizydorczyk.sns.search.domain.post;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.sns.search.infrastructure.query.QueryFilter;

import java.util.List;
import java.util.function.Function;

public class PostSearchQueryResolver implements GraphQLQueryResolver {
    private final Function<QueryFilter[], List<PostSearch>> findPosts;

    PostSearchQueryResolver(Function<QueryFilter[], List<PostSearch>> findPosts) {
        this.findPosts = findPosts;
    }

    public List<PostSearch> findPosts(QueryFilter[] filters) {
        return findPosts.apply(filters);
    }
}

package tk.aizydorczyk.sns.search.domain.post;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.jpqlgenerator.Page;
import tk.aizydorczyk.jpqlgenerator.QueryFilter;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class PostSearchQueryResolver implements GraphQLQueryResolver {
    private final BiFunction<QueryFilter[], Page, List<PostSearch>> findPosts;
    private final Function<Supplier<List<PostSearch>>, List<PostSearch>> runInTransaction;

    PostSearchQueryResolver(BiFunction<QueryFilter[], Page, List<PostSearch>> findPosts,
                            Function<Supplier<List<PostSearch>>, List<PostSearch>> runInTransaction) {
        this.findPosts = findPosts;
        this.runInTransaction = runInTransaction;
    }

    public List<PostSearch> findPosts(QueryFilter[] filters, Page page) {
        return runInTransaction.apply(() -> findPosts.apply(filters, page));
    }
}

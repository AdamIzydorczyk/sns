package tk.aizydorczyk.sns.search.domain.comment;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import tk.aizydorczyk.jpqlgenerator.Page;
import tk.aizydorczyk.jpqlgenerator.QueryFilter;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CommentSearchQueryResolver implements GraphQLQueryResolver {
    private BiFunction<QueryFilter[], Page, List<CommentSearch>> findComments;
    private final Function<Supplier<List<CommentSearch>>, List<CommentSearch>> runInTransaction;

    CommentSearchQueryResolver(BiFunction<QueryFilter[], Page, List<CommentSearch>> findComments,
                               Function<Supplier<List<CommentSearch>>, List<CommentSearch>> runInTransaction) {
        this.runInTransaction = runInTransaction;
        this.findComments = findComments;
    }

    public List<CommentSearch> findComments(QueryFilter[] filters, Page page) {
        return runInTransaction.apply(() -> findComments.apply(filters, page));
    }
}

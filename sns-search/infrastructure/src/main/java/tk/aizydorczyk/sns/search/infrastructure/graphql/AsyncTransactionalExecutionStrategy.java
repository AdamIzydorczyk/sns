package tk.aizydorczyk.sns.search.infrastructure.graphql;

import graphql.ExecutionResult;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionContext;
import graphql.execution.ExecutionStrategyParameters;
import graphql.execution.NonNullableFieldWasNullException;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;

import java.util.concurrent.CompletableFuture;

public class AsyncTransactionalExecutionStrategy extends AsyncExecutionStrategy {
    private final TransactionUtils transactionUtils;

    AsyncTransactionalExecutionStrategy(TransactionUtils transactionUtils) {
        this.transactionUtils = transactionUtils;
    }

    @Override
    public CompletableFuture<ExecutionResult> execute(ExecutionContext executionContext, ExecutionStrategyParameters parameters) throws NonNullableFieldWasNullException {
        return transactionUtils.runInReadOnlyTransaction(() -> super.execute(executionContext, parameters));
    }
}

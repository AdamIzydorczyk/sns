package tk.aizydorczyk.sns.search.infrastructure.graphql;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GraphQLConfiguration {
    @Bean
    public Map<String, ExecutionStrategy> executionStrategies(TransactionUtils transactionUtils) {
        Map<String, ExecutionStrategy> executionStrategyMap = new HashMap<>();
        executionStrategyMap.put("queryExecutionStrategy", asyncExecutionStrategy(transactionUtils));
        return executionStrategyMap;
    }

    @Bean
    public AsyncExecutionStrategy asyncExecutionStrategy(TransactionUtils transactionUtils) {
        return new AsyncTransactionalExecutionStrategy(transactionUtils);
    }
}

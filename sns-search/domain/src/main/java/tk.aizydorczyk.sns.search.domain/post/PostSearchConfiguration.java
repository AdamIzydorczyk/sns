package tk.aizydorczyk.sns.search.domain.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.search.infrastructure.query.DynamicJpqlQueryGenerator;

import javax.persistence.EntityManager;

@Configuration
public class PostSearchConfiguration {
    @Bean
    public PostSearchQueryResolver postQueryResolver(EntityManager entityManager,
                                                     Mapper mapper,
                                                     TransactionUtils transactionUtils) {
        final DynamicJpqlQueryGenerator<PostSearch> dynamicJpqlQueryGenerator = new DynamicJpqlQueryGenerator<>(entityManager, PostSearch.class, mapper);
        return new PostSearchQueryResolver(dynamicJpqlQueryGenerator::findEntities, transactionUtils::runInReadOnlyTransaction);
    }

}

package tk.aizydorczyk.sns.search.domain.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.jpqlgenerator.DynamicJpqlQueryGenerator;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.search.infrastructure.jpa.EntityTypesProvider;

import javax.persistence.EntityManager;

@Configuration
public class CommentSearchConfiguration {
    @Bean
    public CommentSearchQueryResolver commentQueryResolver(EntityManager entityManager,
                                                           Mapper mapper,
                                                           TransactionUtils transactionUtils,
                                                           EntityTypesProvider entityTypesProvider) {
        final DynamicJpqlQueryGenerator<CommentSearch> dynamicJpqlQueryGenerator = new DynamicJpqlQueryGenerator<>(entityManager,
                CommentSearch.class,
                mapper::map,
                entityTypesProvider::getAllEntityTypes);
        return new CommentSearchQueryResolver(dynamicJpqlQueryGenerator::findEntities,
                transactionUtils::runInReadOnlyTransaction);
    }
}

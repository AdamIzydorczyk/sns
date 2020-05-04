package tk.aizydorczyk.sns.search.domain.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

import javax.persistence.EntityManager;

@Configuration
public class CommentSearchConfiguration {
    @Bean
    public CommentSearchQueryResolver commentQueryResolver(EntityManager entityManager,
                                                           Mapper mapper) {
        return new CommentSearchQueryResolver(mapper, entityManager);
    }
}

package tk.aizydorczyk.sns.search.domain.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

import javax.persistence.EntityManager;

@Configuration
public class PostSearchConfiguration {
    @Bean
    public PostSearchQueryResolver postQueryResolver(EntityManager entityManager,
                                                     Mapper mapper) {
        return new PostSearchQueryResolver(mapper, entityManager);
    }

}

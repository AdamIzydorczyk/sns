package tk.aizydorczyk.sns.search.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

import javax.persistence.EntityManager;

@Configuration
public class PostSearchConfiguration {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public PostQueryResolver postQueryResolver(PostSearchRepository postSearchRepository,
                                               Mapper mapper) {
        return new PostQueryResolver(postSearchRepository::findAll,
                mapper, entityManager);
    }

}

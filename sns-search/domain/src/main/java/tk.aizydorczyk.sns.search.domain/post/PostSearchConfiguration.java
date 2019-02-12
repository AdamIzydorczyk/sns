package tk.aizydorczyk.sns.search.domain.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.domain.post.PostDto;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

@Configuration
public class PostSearchConfiguration {
    @Bean
    public PostQueryResolver postQueryResolver(PostSearchRepository postSearchRepository,
                                               Mapper mapper) {
        return new PostQueryResolver(postSearchRepository::findAll,
                post -> mapper.map(post, PostDto.class));
    }

}

package tk.aizydorczyk.sns.operation.domain.post.update;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class UpdatePostCommandConfiguration {

    private final PostRepository postRepository;
    private final Mapper mapper;

    public UpdatePostCommandConfiguration(PostRepository postRepository,
                                          Mapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Bean
    public UpdatePostCommand updatePostCommand() {
        return new UpdatePostCommand(postRepository::findById,
                postRepository::saveAndFlush,
                post -> mapper.map(post, PostDto.class),
                mapper);
    }
}

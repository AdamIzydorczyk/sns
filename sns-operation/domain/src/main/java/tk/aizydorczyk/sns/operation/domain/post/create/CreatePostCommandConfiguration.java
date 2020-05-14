package tk.aizydorczyk.sns.operation.domain.post.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class CreatePostCommandConfiguration {

    private final PostRepository postRepository;
    private final Mapper mapper;

    public CreatePostCommandConfiguration(PostRepository postRepository,
                                          Mapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Bean
    public CreatePostCommand createPostCommand() {
        return new CreatePostCommand(postRepository::save,
                post -> mapper.map(post, PostDto.class),
                mapper);
    }
}

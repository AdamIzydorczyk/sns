package tk.aizydorczyk.sns.operation.domain.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

@Configuration
class PostConfiguration {
    private final PostRepository postRepository;
    private final Mapper mapper;

    public PostConfiguration(PostRepository postRepository,
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

    @Bean
    public UpdatePostCommand updatePostCommand() {
        return new UpdatePostCommand(postRepository::findById,
                post -> mapper.map(post, PostDto.class),
                mapper);
    }

    @Bean
    public DeletePostCommand deletePostCommand() {
        return new DeletePostCommand(postRepository::deleteById);
    }
}

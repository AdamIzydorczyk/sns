package tk.aizydorczyk.sns.operation.domain.post.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class DeletePostCommandConfiguration {

    private final PostRepository postRepository;

    public DeletePostCommandConfiguration(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Bean
    public DeletePostCommand deletePostCommand() {
        return new DeletePostCommand(postRepository::deleteById);
    }
}

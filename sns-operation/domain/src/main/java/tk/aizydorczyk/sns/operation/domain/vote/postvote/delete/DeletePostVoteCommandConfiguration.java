package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;
import tk.aizydorczyk.sns.operation.domain.vote.postvote.PostVoteRepository;

@Configuration
class DeletePostVoteCommandConfiguration {

    private final PostRepository postRepository;
    private final PostVoteRepository postVoteRepository;

    public DeletePostVoteCommandConfiguration(PostRepository postRepository,
                                              PostVoteRepository postVoteRepository) {
        this.postRepository = postRepository;
        this.postVoteRepository = postVoteRepository;
    }

    @Bean
    public DeletePostVoteCommand deletePostVoteCommand() {
        return new DeletePostVoteCommand(postVoteRepository::deleteById,
                postRepository::findById);
    }
}

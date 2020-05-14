package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;

@Configuration
public class PostVoteCommandsConfiguration {

    private final PostRepository postRepository;
    private final PostVoteRepository postVoteRepository;
    private final Mapper mapper;

    public PostVoteCommandsConfiguration(PostRepository postRepository,
                                         PostVoteRepository postVoteRepository,
                                         Mapper mapper) {
        this.postRepository = postRepository;
        this.postVoteRepository = postVoteRepository;
        this.mapper = mapper;
    }

    @Bean
    public CreatePostVoteCommand createPostVoteCommand() {
        return new CreatePostVoteCommand(postRepository::findById,
                postVoteRepository::save,
                vote -> mapper.map(vote, VoteDto.class),
                mapper);
    }

    @Bean
    public UpdatePostVoteCommand updatePostVoteCommand() {
        return new UpdatePostVoteCommand(postVoteRepository::findById,
                postRepository::findById,
                postVoteRepository::saveAndFlush,
                vote -> mapper.map(vote, VoteDto.class),
                mapper);
    }

    @Bean
    public DeletePostVoteCommand deletePostVoteCommand() {
        return new DeletePostVoteCommand(postVoteRepository::deleteById,
                postRepository::findById);
    }
}

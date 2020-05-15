package tk.aizydorczyk.sns.operation.domain.vote.postvote.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.postvote.PostVoteRepository;

@Configuration
class CreatePostVoteCommandConfiguration {

    private final PostRepository postRepository;
    private final PostVoteRepository postVoteRepository;
    private final Mapper mapper;

    public CreatePostVoteCommandConfiguration(PostRepository postRepository,
                                              PostVoteRepository postVoteRepository,
                                              Mapper mapper) {
        this.postRepository = postRepository;
        this.postVoteRepository = postVoteRepository;
        this.mapper = mapper;
    }

    @Bean
    public CreatePostVoteCommand createPostVoteCommand() {
        return new CreatePostVoteCommand(
                postVoteRepository::findByPostIdAndCreatedBy,
                postRepository::findById,
                postVoteRepository::save,
                vote -> mapper.map(vote, VoteDto.class),
                mapper);
    }
}

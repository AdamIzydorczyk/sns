package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.CommentRepository;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;

@Configuration
public class CommentVoteCommandsConfiguration {
    private final CommentRepository commentRepository;
    private final CommentVoteRepository commentVoteRepository;
    private final Mapper mapper;

    public CommentVoteCommandsConfiguration(CommentRepository commentRepository,
                                            CommentVoteRepository commentVoteRepository,
                                            Mapper mapper) {
        this.commentRepository = commentRepository;
        this.commentVoteRepository = commentVoteRepository;
        this.mapper = mapper;
    }

    @Bean
    public CreateCommentVoteCommand createCommentVoteCommand() {
        return new CreateCommentVoteCommand(commentRepository::findById,
                commentVoteRepository::save,
                vote -> mapper.map(vote, VoteDto.class),
                mapper);
    }

    @Bean
    public UpdateCommentVoteCommand updateCommentVoteCommand() {
        return new UpdateCommentVoteCommand(commentVoteRepository::findById,
                commentRepository::findById,
                commentVoteRepository::saveAndFlush,
                vote -> mapper.map(vote, VoteDto.class),
                mapper);
    }

    @Bean
    public DeleteCommentVoteCommand deleteCommentVoteCommand() {
        return new DeleteCommentVoteCommand(commentVoteRepository::deleteById,
                commentRepository::findById);
    }
}

package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.CommentRepository;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.commentvote.CommentVoteRepository;

@Configuration
class CreateCommentVoteCommandConfiguration {

    private final CommentRepository commentRepository;
    private final CommentVoteRepository commentVoteRepository;
    private final Mapper mapper;

    public CreateCommentVoteCommandConfiguration(CommentRepository commentRepository, CommentVoteRepository commentVoteRepository, Mapper mapper) {
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

}

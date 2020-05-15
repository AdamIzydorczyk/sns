package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.operation.domain.comment.CommentRepository;
import tk.aizydorczyk.sns.operation.domain.vote.commentvote.CommentVoteRepository;

@Configuration
class DeleteCommentVoteCommandConfiguration {

    private final CommentRepository commentRepository;
    private final CommentVoteRepository commentVoteRepository;

    public DeleteCommentVoteCommandConfiguration(CommentRepository commentRepository,
                                                 CommentVoteRepository commentVoteRepository) {
        this.commentRepository = commentRepository;
        this.commentVoteRepository = commentVoteRepository;
    }

    @Bean
    public DeleteCommentVoteCommand deleteCommentVoteCommand() {
        return new DeleteCommentVoteCommand(commentVoteRepository::deleteByCommentIdAndCreatedBy,
                commentRepository::findById);
    }

}

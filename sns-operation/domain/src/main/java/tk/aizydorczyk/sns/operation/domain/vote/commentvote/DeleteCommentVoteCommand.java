package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class DeleteCommentVoteCommand extends BaseDeleteDependentCommand<Comment> {
    protected DeleteCommentVoteCommand(Consumer<Long> deleteById,
                                       Function<Long, Optional<Comment>> findParentById) {
        super(deleteById, findParentById);
    }
}

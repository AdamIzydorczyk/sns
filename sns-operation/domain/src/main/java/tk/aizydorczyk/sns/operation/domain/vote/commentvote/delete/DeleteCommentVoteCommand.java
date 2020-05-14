package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.comment.delete.DeleteCommentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

class DeleteCommentVoteCommand extends BaseDeleteDependentCommand<Comment> {
    protected DeleteCommentVoteCommand(Consumer<Long> deleteById,
                                       Function<Long, Optional<Comment>> findParentById) {
        super(deleteById, findParentById);
    }

    @Override
    public SystemEvent prepareEvent(Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        return new DeleteCommentEvent(parentId, dependentId, auditingInformation);
    }
}

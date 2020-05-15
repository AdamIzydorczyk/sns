package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.BaseDeleteVoteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

class DeleteCommentVoteCommand extends BaseDeleteVoteCommand<Comment> {
    protected DeleteCommentVoteCommand(BiConsumer<Long, UUID> deleteByCommentIdAndCreatedBy,
                                       Function<Long, Optional<Comment>> findParentById) {
        super(deleteByCommentIdAndCreatedBy, findParentById);
    }

    @Override
    public SystemEvent prepareEvent(Long parentId, AuditingInformation auditingInformation) {
        return new DeleteCommentVoteEvent(parentId, auditingInformation);
    }
}

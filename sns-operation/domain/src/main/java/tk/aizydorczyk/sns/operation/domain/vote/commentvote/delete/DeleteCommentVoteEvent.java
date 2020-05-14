package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseDeleteDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeleteCommentVoteEvent extends BaseDeleteDependentEvent<DeleteCommentVoteCommand> {
    public DeleteCommentVoteEvent(Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        super(DeleteCommentVoteCommand.class, parentId, dependentId, auditingInformation);
    }
}

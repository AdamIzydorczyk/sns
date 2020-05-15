package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import tk.aizydorczyk.sns.operation.domain.vote.BaseDeleteVoteEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeleteCommentVoteEvent extends BaseDeleteVoteEvent<DeleteCommentVoteCommand> {
    public DeleteCommentVoteEvent(Long parentId, AuditingInformation auditingInformation) {
        super(DeleteCommentVoteCommand.class, parentId, auditingInformation);
    }
}

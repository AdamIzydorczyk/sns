package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import tk.aizydorczyk.sns.operation.domain.vote.BaseDeleteVoteEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeletePostVoteEvent extends BaseDeleteVoteEvent<DeletePostVoteCommand> {
    public DeletePostVoteEvent(Long parentId, AuditingInformation auditingInformation) {
        super(DeletePostVoteCommand.class, parentId, auditingInformation);
    }
}

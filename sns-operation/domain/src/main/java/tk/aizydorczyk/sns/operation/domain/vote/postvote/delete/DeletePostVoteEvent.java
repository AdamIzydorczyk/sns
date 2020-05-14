package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseDeleteDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeletePostVoteEvent extends BaseDeleteDependentEvent<DeletePostVoteCommand> {
    public DeletePostVoteEvent(Long parentId,
                               Long dependentId,
                               AuditingInformation auditingInformation) {
        super(DeletePostVoteCommand.class, parentId, dependentId, auditingInformation);
    }
}

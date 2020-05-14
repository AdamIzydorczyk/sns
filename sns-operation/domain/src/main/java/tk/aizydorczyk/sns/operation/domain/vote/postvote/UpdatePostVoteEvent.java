package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseUpdateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class UpdatePostVoteEvent extends BaseUpdateDependentEvent<VoteDto, UpdatePostVoteCommand> {
    public UpdatePostVoteEvent(VoteDto dto, Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        super(UpdatePostVoteCommand.class, VoteDto.class, dto, parentId, dependentId, auditingInformation);
    }
}

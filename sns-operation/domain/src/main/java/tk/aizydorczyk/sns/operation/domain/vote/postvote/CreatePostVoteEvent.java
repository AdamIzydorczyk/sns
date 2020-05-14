package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseCreateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreatePostVoteEvent extends BaseCreateDependentEvent<VoteDto, CreatePostVoteCommand> {
    public CreatePostVoteEvent(VoteDto dto, Long parentId, AuditingInformation auditingInformation) {
        super(CreatePostVoteCommand.class, VoteDto.class, dto, parentId, auditingInformation);
    }
}

package tk.aizydorczyk.sns.operation.domain.vote.postvote.create;

import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteEvent;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreatePostVoteEvent extends BaseCreateVoteEvent<CreatePostVoteCommand> {

    public CreatePostVoteEvent(VoteDto dto,
                               Long parentId,
                               AuditingInformation auditingInformation) {
        super(CreatePostVoteCommand.class, dto, parentId, auditingInformation);
    }
}

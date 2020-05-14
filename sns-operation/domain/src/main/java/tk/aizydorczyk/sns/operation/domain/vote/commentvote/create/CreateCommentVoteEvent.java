package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseCreateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreateCommentVoteEvent extends BaseCreateDependentEvent<VoteDto, CreateCommentVoteCommand> {
    public CreateCommentVoteEvent(VoteDto dto, Long parentId, AuditingInformation auditingInformation) {
        super(CreateCommentVoteCommand.class, VoteDto.class, dto, parentId, auditingInformation);
    }
}

package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseUpdateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class UpdateCommentVoteEvent extends BaseUpdateDependentEvent<VoteDto, UpdateCommentVoteCommand> {
    public UpdateCommentVoteEvent(VoteDto dto, Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        super(UpdateCommentVoteCommand.class, VoteDto.class, dto, parentId, dependentId, auditingInformation);
    }
}

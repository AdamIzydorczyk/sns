package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteEvent;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreateCommentVoteEvent extends BaseCreateVoteEvent<CreateCommentVoteCommand> {
    public CreateCommentVoteEvent(VoteDto dto,
                                  Long parentId,
                                  AuditingInformation auditingInformation) {
        super(CreateCommentVoteCommand.class, dto, parentId, auditingInformation);
    }
}

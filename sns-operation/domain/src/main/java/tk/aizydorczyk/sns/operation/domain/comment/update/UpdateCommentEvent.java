package tk.aizydorczyk.sns.operation.domain.comment.update;

import tk.aizydorczyk.sns.operation.domain.comment.CommentDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseUpdateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class UpdateCommentEvent extends BaseUpdateDependentEvent<CommentDto, UpdateCommentCommand> {
    public UpdateCommentEvent(CommentDto dto,
                              Long parentId,
                              Long dependentId,
                              AuditingInformation auditingInformation) {
        super(UpdateCommentCommand.class, CommentDto.class, dto, parentId, dependentId, auditingInformation);
    }
}

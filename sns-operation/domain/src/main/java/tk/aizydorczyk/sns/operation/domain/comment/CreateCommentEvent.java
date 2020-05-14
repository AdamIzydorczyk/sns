package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseCreateDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreateCommentEvent extends BaseCreateDependentEvent<CommentDto, CreateCommentCommand> {
    public CreateCommentEvent(CommentDto dto, Long parentId, AuditingInformation auditingInformation) {
        super(CreateCommentCommand.class, CommentDto.class, dto, parentId, auditingInformation);
    }
}

package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseDeleteDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeleteCommentEvent extends BaseDeleteDependentEvent<DeleteCommentCommand> {
    public DeleteCommentEvent(Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        super(DeleteCommentCommand.class, parentId, dependentId, auditingInformation);
    }
}

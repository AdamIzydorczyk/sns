package tk.aizydorczyk.sns.operation.domain.post.delete;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseDeleteEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class DeletePostEvent extends BaseDeleteEvent<DeletePostCommand> {
    public DeletePostEvent(Long id,
                           AuditingInformation auditingInformation) {
        super(DeletePostCommand.class, id, auditingInformation);
    }
}

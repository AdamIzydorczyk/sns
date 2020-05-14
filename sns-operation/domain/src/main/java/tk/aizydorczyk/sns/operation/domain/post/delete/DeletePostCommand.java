package tk.aizydorczyk.sns.operation.domain.post.delete;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.function.Consumer;

class DeletePostCommand extends BaseDeleteCommand {
    DeletePostCommand(Consumer<Long> deleteById) {
        super(deleteById);
    }

    @Override
    public SystemEvent prepareEvent(Long id, AuditingInformation auditingInformation) {
        return new DeletePostEvent(id, auditingInformation);
    }
}

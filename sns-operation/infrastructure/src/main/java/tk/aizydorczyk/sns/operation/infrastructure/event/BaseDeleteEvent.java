package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public abstract class BaseDeleteEvent<CommandType extends BaseDeleteCommand>
        implements SystemEvent {

    private final Class<CommandType> commandClass;
    private final Long id;
    private final AuditingInformation auditingInformation;

    public BaseDeleteEvent(Class<CommandType> commandClass,
                           Long id,
                           AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.id = Objects.requireNonNull(id);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CommandType> getCommandClass() {
        return commandClass;
    }

    public Long getId() {
        return id;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

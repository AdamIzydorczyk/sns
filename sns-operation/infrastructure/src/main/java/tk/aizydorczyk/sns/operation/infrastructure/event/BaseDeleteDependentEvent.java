package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public abstract class BaseDeleteDependentEvent<CommandType extends BaseDeleteDependentCommand<?>> implements SystemEvent {

    private final Class<CommandType> commandClass;
    private final Long parentId;
    private final Long dependentId;
    private final AuditingInformation auditingInformation;

    public BaseDeleteDependentEvent(Class<CommandType> commandClass,
                                    Long parentId,
                                    Long dependentId,
                                    AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.parentId = Objects.requireNonNull(parentId);
        this.dependentId = Objects.requireNonNull(dependentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CommandType> getCommandClass() {
        return commandClass;
    }

    public Long getParentId() {
        return parentId;
    }

    public Long getDependentId() {
        return dependentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

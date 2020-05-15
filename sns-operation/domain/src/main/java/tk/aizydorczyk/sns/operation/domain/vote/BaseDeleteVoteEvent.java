package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public abstract class BaseDeleteVoteEvent<CommandType extends BaseDeleteVoteCommand<?>> implements SystemEvent {

    private final Class<CommandType> commandClass;
    private final Long parentId;
    private final AuditingInformation auditingInformation;

    public BaseDeleteVoteEvent(Class<CommandType> commandClass,
                               Long parentId,
                               AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.parentId = Objects.requireNonNull(parentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CommandType> getCommandClass() {
        return commandClass;
    }

    public Long getParentId() {
        return parentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

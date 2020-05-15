package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public abstract class BaseUpdateDependentEvent<DtoType extends BaseDto,
        CommandType extends BaseUpdateDependentCommand<DtoType, ?, ?>>
        implements SystemEvent {

    private final Class<CommandType> commandClass;
    private final Class<DtoType> dtoClass;
    private final DtoType dto;
    private final Long parentId;
    private final Long dependentId;
    private final AuditingInformation auditingInformation;

    public BaseUpdateDependentEvent(Class<CommandType> commandClass,
                                    DtoType dto,
                                    Long parentId,
                                    Long dependentId,
                                    AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.dto = Objects.requireNonNull(dto);
        this.dtoClass = (Class<DtoType>) dto.getClass();
        this.parentId = Objects.requireNonNull(parentId);
        this.dependentId = Objects.requireNonNull(dependentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CommandType> getCommandClass() {
        return commandClass;
    }

    public Class<DtoType> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
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

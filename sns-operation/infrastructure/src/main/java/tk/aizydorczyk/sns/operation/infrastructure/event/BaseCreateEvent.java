package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public abstract class BaseCreateEvent<DtoType extends BaseDto, CommandType extends BaseCreateCommand<DtoType, ?>> implements SystemEvent {

    private final Class<CommandType> commandClass;
    private final Class<DtoType> dtoClass;
    private final DtoType dto;
    private final AuditingInformation auditingInformation;

    public BaseCreateEvent(Class<CommandType> commandClass,
                           DtoType dto,
                           AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.dto = Objects.requireNonNull(dto);
        this.dtoClass = (Class<DtoType>) dto.getClass();
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

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

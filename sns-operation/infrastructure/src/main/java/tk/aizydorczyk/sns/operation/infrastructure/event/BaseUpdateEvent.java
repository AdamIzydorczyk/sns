package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public abstract class BaseUpdateEvent<DtoType extends BaseDto, CommandClass extends BaseUpdateCommand<DtoType, ?>> implements SystemEvent {

    private final Class<CommandClass> commandClass;
    private final Class<DtoType> dtoClass;
    private final DtoType dto;
    private final Long id;
    private final AuditingInformation auditingInformation;

    public BaseUpdateEvent(Class<CommandClass> commandClass,
                           DtoType dto,
                           Long id,
                           AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.dto = Objects.requireNonNull(dto);
        this.dtoClass = (Class<DtoType>) dto.getClass();
        this.id = Objects.requireNonNull(id);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CommandClass> getCommandClass() {
        return commandClass;
    }

    public Class<DtoType> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
    }

    public Long getId() {
        return id;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

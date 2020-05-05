package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public class CreateCommandEvent<DtoType extends BaseDto> implements SystemEvent {

    private final Class<? extends BaseCreateCommand> createCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final AuditingInformation auditingInformation;

    public CreateCommandEvent(Class<? extends BaseCreateCommand> createCommandClass,
                              Class<? extends BaseDto> dtoClass,
                              DtoType dto,
                              AuditingInformation auditingInformation) {
        this.createCommandClass = Objects.requireNonNull(createCommandClass);
        this.dtoClass = Objects.requireNonNull(dtoClass);
        this.dto = Objects.requireNonNull(dto);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseCreateCommand> getCreateCommandClass() {
        return createCommandClass;
    }

    public Class<? extends BaseDto> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

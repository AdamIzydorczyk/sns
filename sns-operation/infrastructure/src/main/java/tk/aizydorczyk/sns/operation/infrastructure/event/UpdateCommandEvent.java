package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public class UpdateCommandEvent<DtoType extends BaseDto> implements SystemEvent {

    private final Class<? extends BaseUpdateCommand> updateCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final Long id;
    private final AuditingInformation auditingInformation;

    public UpdateCommandEvent(Class<? extends BaseUpdateCommand> updateCommandClass,
                              Class<? extends BaseDto> dtoClass,
                              DtoType dto,
                              Long id,
                              AuditingInformation auditingInformation) {
        this.updateCommandClass = Objects.requireNonNull(updateCommandClass);
        this.dtoClass = Objects.requireNonNull(dtoClass);
        this.dto = Objects.requireNonNull(dto);
        this.id = Objects.requireNonNull(id);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseUpdateCommand> getUpdateCommandClass() {
        return updateCommandClass;
    }

    public Class<? extends BaseDto> getDtoClass() {
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

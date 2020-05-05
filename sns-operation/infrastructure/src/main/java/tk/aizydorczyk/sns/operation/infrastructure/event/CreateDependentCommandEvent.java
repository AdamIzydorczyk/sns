package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public class CreateDependentCommandEvent<DtoType extends BaseDto> implements SystemEvent {

    private final Class<? extends BaseCreateDependentCommand> createDependentCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final Long parentId;
    private final AuditingInformation auditingInformation;

    public CreateDependentCommandEvent(Class<? extends BaseCreateDependentCommand> createDependentCommandClass,
                                       Class<? extends BaseDto> dtoClass,
                                       DtoType dto,
                                       Long parentId,
                                       AuditingInformation auditingInformation) {
        this.createDependentCommandClass = Objects.requireNonNull(createDependentCommandClass);
        this.dtoClass = Objects.requireNonNull(dtoClass);
        this.dto = Objects.requireNonNull(dto);
        this.parentId = Objects.requireNonNull(parentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseCreateDependentCommand> getCreateDependentCommandClass() {
        return createDependentCommandClass;
    }

    public Class<? extends BaseDto> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
    }

    public Long getParentId() {
        return parentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

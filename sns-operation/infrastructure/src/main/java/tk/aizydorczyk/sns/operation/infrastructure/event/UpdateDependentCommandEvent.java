package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;

public class UpdateDependentCommandEvent<DtoType extends BaseDto> implements SystemEvent {

    private final Class<? extends BaseUpdateDependentCommand> updateDependentCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final Long parentId;
    private final Long dependentId;
    private final AuditingInformation auditingInformation;

    public UpdateDependentCommandEvent(Class<? extends BaseUpdateDependentCommand> updateDependentCommandClass,
                                       Class<? extends BaseDto> dtoClass,
                                       DtoType dto,
                                       Long parentId,
                                       Long dependentId,
                                       AuditingInformation auditingInformation) {
        this.updateDependentCommandClass = Objects.requireNonNull(updateDependentCommandClass);
        this.dtoClass = Objects.requireNonNull(dtoClass);
        this.dto = Objects.requireNonNull(dto);
        this.parentId = Objects.requireNonNull(parentId);
        this.dependentId = Objects.requireNonNull(dependentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseUpdateDependentCommand> getUpdateDependentCommandClass() {
        return updateDependentCommandClass;
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

    public Long getDependentId() {
        return dependentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

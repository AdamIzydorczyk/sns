package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public class DeleteDependentCommandEvent implements SystemEvent {

    private final Class<? extends BaseDeleteDependentCommand> deleteDependentCommandClass;
    private final Long parentId;
    private final Long dependentId;
    private final AuditingInformation auditingInformation;

    public DeleteDependentCommandEvent(Class<? extends BaseDeleteDependentCommand> deleteDependentCommandClass,
                                       Long parentId,
                                       Long dependentId,
                                       AuditingInformation auditingInformation) {
        this.deleteDependentCommandClass = Objects.requireNonNull(deleteDependentCommandClass);
        this.parentId = Objects.requireNonNull(parentId);
        this.dependentId = Objects.requireNonNull(dependentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseDeleteDependentCommand> getDeleteDependentCommandClass() {
        return deleteDependentCommandClass;
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

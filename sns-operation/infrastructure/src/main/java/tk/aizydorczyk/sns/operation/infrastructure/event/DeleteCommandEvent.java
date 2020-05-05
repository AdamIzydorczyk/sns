package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public class DeleteCommandEvent implements SystemEvent {

    private final Class<? extends BaseDeleteCommand> deleteCommandClass;
    private final Long id;
    private final AuditingInformation auditingInformation;

    public DeleteCommandEvent(Class<? extends BaseDeleteCommand> deleteCommandClass,
                              Long id,
                              AuditingInformation auditingInformation) {
        this.deleteCommandClass = Objects.requireNonNull(deleteCommandClass);
        this.id = Objects.requireNonNull(id);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends BaseDeleteCommand> getDeleteCommandClass() {
        return deleteCommandClass;
    }

    public Long getId() {
        return id;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

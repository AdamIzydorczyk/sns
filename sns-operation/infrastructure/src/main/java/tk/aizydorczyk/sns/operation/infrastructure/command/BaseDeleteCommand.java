package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class BaseDeleteCommand {
    private final Consumer<Long> deleteById;

    protected BaseDeleteCommand(Consumer<Long> deleteById) {
        this.deleteById = Objects.requireNonNull(deleteById);
    }

    public final void execute(Long id) {
        deleteById.accept(id);
    }

    public abstract SystemEvent prepareEvent(Long id, AuditingInformation auditingInformation);
}

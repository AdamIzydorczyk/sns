package tk.aizydorczyk.sns.operation.infrastructure.command;

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
}

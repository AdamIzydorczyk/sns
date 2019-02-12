package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseDeleteDependentCommand<ParentEntityType extends BaseEntity> {
    private final Consumer<Long> deleteById;
    private final Function<Long, Optional<ParentEntityType>> findParentById;

    protected BaseDeleteDependentCommand(Consumer<Long> deleteById, Function<Long, Optional<ParentEntityType>> findParentById) {
        this.deleteById = deleteById;
        this.findParentById = findParentById;
    }

    public final void execute(Long parentId, Long entityId) {
        findParentById.apply(parentId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        deleteById.accept(entityId);
    }
}

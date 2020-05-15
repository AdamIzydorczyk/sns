package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class BaseDeleteVoteCommand<ParentEntityType extends BaseEntity<?>> {
    private final BiConsumer<Long, UUID> deleteByParentIdAndCreatedBy;
    private final Function<Long, Optional<ParentEntityType>> findParentById;

    protected BaseDeleteVoteCommand(BiConsumer<Long, UUID> deleteByParentIdAndCreatedBy,
                                    Function<Long, Optional<ParentEntityType>> findParentById) {
        this.deleteByParentIdAndCreatedBy = Objects.requireNonNull(deleteByParentIdAndCreatedBy);
        this.findParentById = Objects.requireNonNull(findParentById);
    }

    public final void execute(Long parentId,
                              UUID userUuid) {
        findParentById.apply(parentId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        deleteByParentIdAndCreatedBy.accept(parentId, userUuid);
    }

    public abstract SystemEvent prepareEvent(Long parentId, AuditingInformation auditingInformation);
}

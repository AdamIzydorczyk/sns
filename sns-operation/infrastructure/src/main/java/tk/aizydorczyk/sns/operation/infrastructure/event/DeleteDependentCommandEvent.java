package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class DeleteDependentCommandEvent implements SystemEvent {
    private final Class<? extends BaseDeleteDependentCommand> deleteDependentCommandClass;
    private final Long parentId;
    private final Long dependentId;
    private final long executionTime;
    private final UUID userUuid;

    public DeleteDependentCommandEvent(Class<? extends BaseDeleteDependentCommand> deleteDependentCommandClass,
                                       Long parentId,
                                       Long dependentId,
                                       LocalDateTime executionTime,
                                       UUID userUuid) {
        this.deleteDependentCommandClass = deleteDependentCommandClass;
        this.parentId = parentId;
        this.dependentId = dependentId;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
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

    public long getExecutionTime() {
        return executionTime;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
}

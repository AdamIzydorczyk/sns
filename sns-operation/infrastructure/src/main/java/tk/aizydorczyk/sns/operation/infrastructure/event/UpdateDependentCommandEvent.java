package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class UpdateDependentCommandEvent<DtoType extends BaseDto> implements SystemEvent {
    private final Class<? extends BaseUpdateDependentCommand> updateDependentCommandClass;
    private final Class<? extends BaseUpdateDependentCommand> dtoClass;
    private final DtoType dto;
    private final Long parentId;
    private final Long dependentId;
    private final long executionTime;
    private final UUID userUuid;

    public UpdateDependentCommandEvent(Class<? extends BaseUpdateDependentCommand> updateDependentCommandClass,
                                       Class<? extends BaseDto> dtoClass,
                                       DtoType dto,
                                       Long parentId,
                                       Long dependentId, LocalDateTime executionTime,
                                       UUID userUuid) {
        this.updateDependentCommandClass = updateDependentCommandClass;
        this.dtoClass = updateDependentCommandClass;
        this.dto = dto;
        this.parentId = parentId;
        this.dependentId = dependentId;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
    }

    public Class<? extends BaseUpdateDependentCommand> getUpdateDependentCommandClass() {
        return updateDependentCommandClass;
    }

    public Class<? extends BaseUpdateDependentCommand> getDtoClass() {
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

    public long getExecutionTime() {
        return executionTime;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
}

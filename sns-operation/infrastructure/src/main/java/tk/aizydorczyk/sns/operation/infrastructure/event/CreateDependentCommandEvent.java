package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class CreateDependentCommandEvent<DtoType extends BaseDto> implements SystemEvent {
    private final Class<? extends BaseCreateDependentCommand> createDependentCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final Long parentId;
    private final long executionTime;
    private final UUID userUuid;

    public CreateDependentCommandEvent(Class<? extends BaseCreateDependentCommand> createDependentCommandClass,
                                       Class<? extends BaseDto> dtoClass,
                                       DtoType dto,
                                       Long parentId,
                                       LocalDateTime executionTime,
                                       UUID userUuid) {
        this.createDependentCommandClass = createDependentCommandClass;
        this.dtoClass = dtoClass;
        this.dto = dto;
        this.parentId = parentId;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
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

    public long getExecutionTime() {
        return executionTime;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
}

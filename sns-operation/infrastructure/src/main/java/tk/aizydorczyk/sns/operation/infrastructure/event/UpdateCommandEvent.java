package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class UpdateCommandEvent<DtoType extends BaseDto> implements SystemEvent {
    private final Class<? extends BaseUpdateCommand> updateCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final Long id;
    private final long executionTime;
    private final UUID userUuid;

    public UpdateCommandEvent(Class<? extends BaseUpdateCommand> updateCommandClass,
                              Class<? extends BaseDto> dtoClass,
                              DtoType dto,
                              Long id,
                              LocalDateTime executionTime,
                              UUID userUuid) {
        this.updateCommandClass = updateCommandClass;
        this.dtoClass = dtoClass;
        this.dto = dto;
        this.id = id;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
    }

    public Class<? extends BaseUpdateCommand> getUpdateCommandClass() {
        return updateCommandClass;
    }

    public Class<? extends BaseDto> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
    }

    public Long getId() {
        return id;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
}

package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class CreateCommandEvent<DtoType extends BaseDto> implements SystemEvent {

    private final Class<? extends BaseCreateCommand> createCommandClass;
    private final Class<? extends BaseDto> dtoClass;
    private final DtoType dto;
    private final long executionTime;
    private final UUID userUuid;

    public CreateCommandEvent(Class<? extends BaseCreateCommand> createCommandClass,
                              Class<? extends BaseDto> dtoClass,
                              DtoType dto,
                              LocalDateTime executionTime, UUID userUuid) {
        this.createCommandClass = createCommandClass;
        this.dtoClass = dtoClass;
        this.dto = dto;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
    }

    public Class<? extends BaseCreateCommand> getCreateCommandClass() {
        return createCommandClass;
    }

    public Class<? extends BaseDto> getDtoClass() {
        return dtoClass;
    }

    public DtoType getDto() {
        return dto;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public UUID getUserUuid() {
        return userUuid;
    }
}

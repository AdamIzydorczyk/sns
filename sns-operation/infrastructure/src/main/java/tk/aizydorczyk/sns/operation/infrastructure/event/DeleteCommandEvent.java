package tk.aizydorczyk.sns.operation.infrastructure.event;

import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class DeleteCommandEvent implements SystemEvent {
    private final Class<? extends BaseDeleteCommand> deleteCommandClass;
    private final Long id;
    private final long executionTime;
    private final UUID userUuid;

    public DeleteCommandEvent(Class<? extends BaseDeleteCommand> deleteCommandClass,
                              Long id,
                              LocalDateTime executionTime,
                              UUID userUuid) {
        this.deleteCommandClass = deleteCommandClass;
        this.id = id;
        this.executionTime = executionTime.toEpochSecond(ZoneOffset.UTC);
        this.userUuid = userUuid;
    }

    public Class<? extends BaseDeleteCommand> getDeleteCommandClass() {
        return deleteCommandClass;
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

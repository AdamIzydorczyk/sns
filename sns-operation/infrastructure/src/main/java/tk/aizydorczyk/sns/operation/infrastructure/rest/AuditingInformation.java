package tk.aizydorczyk.sns.operation.infrastructure.rest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

public final class AuditingInformation {
    private final UUID userUuid;
    private final LocalDateTime executionTime;

    public AuditingInformation(UUID userUuid,
                               LocalDateTime executionTime) {
        this.userUuid = Objects.requireNonNull(userUuid);
        this.executionTime = Objects.requireNonNull(executionTime);
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public LocalDateTime collectExecutionDateTime() {
        return executionTime;
    }

    public Long getExecutionTime() {
        return executionTime.toEpochSecond(ZoneOffset.UTC);
    }
}

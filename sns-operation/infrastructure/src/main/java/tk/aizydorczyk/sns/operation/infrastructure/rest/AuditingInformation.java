package tk.aizydorczyk.sns.operation.infrastructure.rest;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class AuditingInformation {
    private UUID userUuid;
    private LocalDateTime executionTime;

    public AuditingInformation(UUID userUuid,
                               LocalDateTime executionTime) {
        this.userUuid = Objects.requireNonNull(userUuid);
        this.executionTime = Objects.requireNonNull(executionTime);
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }
}

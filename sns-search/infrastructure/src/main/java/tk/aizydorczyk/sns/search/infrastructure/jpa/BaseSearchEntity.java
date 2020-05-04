package tk.aizydorczyk.sns.search.infrastructure.jpa;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static java.util.Objects.nonNull;

@MappedSuperclass
public class BaseSearchEntity {
    @Id
    private Long id;

    private boolean deleted;

    private UUID createdBy;

    private LocalDateTime createdAt;

    private UUID modifiedBy;

    private LocalDateTime modifiedAt;

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public Long getCreatedAt() {
        return createdAt.toEpochSecond(ZoneOffset.UTC);
    }

    public UUID getModifiedBy() {
        return modifiedBy;
    }

    public Long getModifiedAt() {
        return nonNull(modifiedAt) ? modifiedAt.toEpochSecond(ZoneOffset.UTC) : null;
    }
}

package tk.aizydorczyk.sns.search.domain.post;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
public class PostSearch {
    @Id
    private Long id;

    private boolean deleted;

    private UUID createdBy;

    private LocalDateTime createdAt;

    private UUID modifiedBy;

    private LocalDateTime modifiedAt;

    private String content;

    public PostSearch() {
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public String getContent() {
        return content;
    }
}

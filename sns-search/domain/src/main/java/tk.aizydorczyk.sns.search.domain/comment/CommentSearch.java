package tk.aizydorczyk.sns.search.domain.comment;

import tk.aizydorczyk.sns.search.domain.post.PostSearch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
public class CommentSearch {

    @Id
    private Long id;

    private boolean deleted;

    private UUID createdBy;

    private LocalDateTime createdAt;

    private UUID modifiedBy;

    private LocalDateTime modifiedAt;

    private String comment;

    private int pluses;

    private int minuses;

    @ManyToOne
    private PostSearch post;

    public CommentSearch() {
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

    public String getComment() {
        return comment;
    }

    public int getPluses() {
        return pluses;
    }

    public int getMinuses() {
        return minuses;
    }

    public PostSearch getPost() {
        return post;
    }
}

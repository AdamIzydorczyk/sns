package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.infrastructure.mapper.MappedEntity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class CommentDetails implements MappedEntity {
    @NotNull
    private String comment;
    @NotNull
    private int pluses;
    @NotNull
    private int minuses;

    public CommentDetails() {
    }

    public CommentDetails(String comment, int pluses, int minuses) {
        this.comment = Objects.requireNonNull(comment);
        this.pluses = pluses;
        this.minuses = minuses;
    }

    @Override
    public Class<?> targetType() {
        return CommentsDetailsDto.class;
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
}

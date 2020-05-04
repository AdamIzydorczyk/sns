package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.infrastructure.mapper.MappedEntity;

import javax.persistence.Embeddable;

@Embeddable
public class CommentDetails implements MappedEntity {
    private String comment;
    private int pluses;
    private int minuses;

    public CommentDetails() {
    }

    public CommentDetails(String comment, int pluses, int minuses) {
        this.comment = comment;
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

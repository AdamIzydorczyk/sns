package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CommentDto extends BaseDto {

    @NotNull
    private String comment;

    public CommentDto() {
    }

    public CommentDto(String comment) {
        this.comment = Objects.requireNonNull(comment);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "comment=" + comment +
                '}';
    }
}

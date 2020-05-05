package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CommentDto extends BaseDto {

    @NotNull
    private CommentsDetailsDto details;

    public CommentDto() {
    }

    public CommentDto(CommentsDetailsDto details) {
        this.details = Objects.requireNonNull(details);
    }

    public CommentsDetailsDto getDetails() {
        return details;
    }

    public void setDetails(CommentsDetailsDto details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "details=" + details +
                '}';
    }
}

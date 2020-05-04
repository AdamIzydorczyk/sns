package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

public class CommentDto extends BaseDto {

    private CommentsDetailsDto details;

    public CommentDto() {
    }

    public CommentDto(CommentsDetailsDto details) {
        this.details = details;
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

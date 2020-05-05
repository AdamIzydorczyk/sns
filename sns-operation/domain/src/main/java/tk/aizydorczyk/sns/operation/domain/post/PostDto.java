package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PostDto extends BaseDto {
    @NotNull
    private String content;

    public PostDto() {
    }

    public PostDto(String content) {
        this.content = Objects.requireNonNull(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "content='" + content + '\'' +
                '}';
    }
}

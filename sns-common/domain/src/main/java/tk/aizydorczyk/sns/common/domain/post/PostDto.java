package tk.aizydorczyk.sns.common.domain.post;

import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;

public class PostDto extends BaseDto {
    private String content;

    public PostDto() {
    }

    public PostDto(String content) {
        this.content = content;
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

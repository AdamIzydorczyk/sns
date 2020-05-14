package tk.aizydorczyk.sns.operation.domain.post;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post")
@SQLDelete(sql =
        "UPDATE post " +
                "SET deleted = true " +
                "WHERE id= ? and version= ?")
@Where(clause = "deleted = false")
public class Post extends BaseEntity<PostDto> {

    private String content;

    public Post() {
        super();
    }

    public Post(PostDto postDto,
                Mapper mapper) {
        super(postDto, mapper);
    }

    private Post(Builder builder) {
        this.content = builder.content;
    }

    public static Builder newPost() {
        return new Builder();
    }

    @Override
    public void applyDto(PostDto postDto, Mapper mapper) {
        this.content = postDto.getContent();
    }

    public String getContent() {
        return content;
    }


    public static final class Builder {
        private String content;

        public Post build() {
            return new Post(this);
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }
    }
}

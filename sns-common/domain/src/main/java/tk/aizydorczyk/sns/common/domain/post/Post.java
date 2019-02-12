package tk.aizydorczyk.sns.common.domain.post;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;

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

    public Post(PostDto postDto, Mapper mapper) {
        super(postDto, mapper);
    }

    @Override
    public void applyDto(PostDto postDto, Mapper mapper) {
        this.content = postDto.getContent();
    }

    public String getContent() {
        return content;
    }
}

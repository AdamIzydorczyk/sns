package tk.aizydorczyk.sns.operation.domain.comment;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
@SQLDelete(sql =
        "UPDATE comment " +
                "SET deleted = true " +
                "WHERE id= ? and version= ?")
@Where(clause = "deleted = false")
public class Comment extends BaseDependentEntity<CommentDto, Post> {

    @NotNull
    private String comment;

    @NotNull
    @ManyToOne
    private Post post;

    public Comment() {
        super();
    }

    public Comment(CommentDto commentDto, Mapper mapper) {
        super(commentDto, mapper);
    }

    @Override
    public void applyDto(CommentDto commentDto, Mapper mapper) {
        this.comment = commentDto.getComment();
    }

    @Override
    public void applyParent(Post post) {
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }
}

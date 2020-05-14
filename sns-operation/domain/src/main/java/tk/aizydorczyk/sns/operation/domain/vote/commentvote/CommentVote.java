package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.Vote;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
@SQLDelete(sql =
        "UPDATE vote " +
                "SET deleted = true " +
                "WHERE id= ? and version= ?")
@Where(clause = "deleted = false")
public class CommentVote extends Vote<Comment> {

    @ManyToOne
    private Comment comment;

    public CommentVote() {
        super();
    }

    public CommentVote(VoteDto dto,
                       Mapper mapper) {
        super(dto, mapper);
    }

    @Override
    public void applyParent(Comment comment) {
        this.comment = comment;
    }
}

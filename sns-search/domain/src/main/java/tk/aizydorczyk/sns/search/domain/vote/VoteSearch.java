package tk.aizydorczyk.sns.search.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteTypes;
import tk.aizydorczyk.sns.search.domain.comment.CommentSearch;
import tk.aizydorczyk.sns.search.domain.post.PostSearch;
import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class VoteSearch extends BaseSearchEntity {
    @Enumerated(value = EnumType.STRING)
    private VoteTypes type;

    @ManyToOne
    private CommentSearch comment;

    @ManyToOne
    private PostSearch post;

    public VoteSearch() {
    }

    public VoteTypes getType() {
        return type;
    }

    public CommentSearch getComment() {
        return comment;
    }

    public PostSearch getPost() {
        return post;
    }
}

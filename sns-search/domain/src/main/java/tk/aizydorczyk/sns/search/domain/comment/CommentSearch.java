package tk.aizydorczyk.sns.search.domain.comment;

import org.hibernate.annotations.Formula;
import tk.aizydorczyk.sns.search.domain.post.PostSearch;
import tk.aizydorczyk.sns.search.domain.vote.VoteSearch;
import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "comment")
public class CommentSearch extends BaseSearchEntity {

    private String comment;

    @ManyToOne
    private PostSearch post;

    @OneToMany(mappedBy = "comment")
    private List<VoteSearch> votes;

    @Formula("(select count(v.id) from vote v where v.comment_id = id and v.type = 'UP') ")
    private Long upVotesCount;

    @Formula("(select count(v.id) from vote v where v.comment_id = id and v.type = 'DOWN') ")
    private Long downVotesCount;

    public CommentSearch() {
    }

    public String getComment() {
        return comment;
    }

    public PostSearch getPost() {
        return post;
    }

    public List<VoteSearch> getVotes() {
        return votes;
    }

    public Long getUpVotesCount() {
        return upVotesCount;
    }

    public Long getDownVotesCount() {
        return downVotesCount;
    }
}

package tk.aizydorczyk.sns.search.domain.post;

import org.hibernate.annotations.Formula;
import tk.aizydorczyk.sns.search.domain.comment.CommentSearch;
import tk.aizydorczyk.sns.search.domain.vote.VoteSearch;
import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "post")
public class PostSearch extends BaseSearchEntity {
    private String content;

    @OneToMany(mappedBy = "post")
    private List<CommentSearch> comments;

    @OneToMany(mappedBy = "post")
    private List<VoteSearch> votes;

    @Formula("(select count(v.id) from vote v where v.post_id = id and v.type = 'UP') ")
    private Long upVotesCount;

    @Formula("(select count(v.id) from vote v where v.post_id = id and v.type = 'DOWN') ")
    private Long downVotesCount;

    public PostSearch() {
    }

    public String getContent() {
        return content;
    }

    public List<CommentSearch> getComments() {
        return comments;
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

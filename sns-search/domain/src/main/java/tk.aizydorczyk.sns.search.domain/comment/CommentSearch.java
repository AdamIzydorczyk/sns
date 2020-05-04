package tk.aizydorczyk.sns.search.domain.comment;

import tk.aizydorczyk.sns.search.domain.post.PostSearch;
import tk.aizydorczyk.sns.search.infrastructure.jpa.BaseSearchEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentSearch extends BaseSearchEntity {

    private String comment;

    private int pluses;

    private int minuses;

    @ManyToOne
    private PostSearch post;

    public CommentSearch() {
    }

    public String getComment() {
        return comment;
    }

    public int getPluses() {
        return pluses;
    }

    public int getMinuses() {
        return minuses;
    }

    public PostSearch getPost() {
        return post;
    }
}

package tk.aizydorczyk.sns.search.domain.post;

import tk.aizydorczyk.sns.search.domain.comment.CommentSearch;
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

    public String getContent() {
        return content;
    }

    public List<CommentSearch> getComments() {
        return comments;
    }
}

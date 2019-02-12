package tk.aizydorczyk.sns.search.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.aizydorczyk.sns.common.domain.comment.Comment;

public interface CommentSearchRepository extends JpaRepository<Comment, Long> {
}

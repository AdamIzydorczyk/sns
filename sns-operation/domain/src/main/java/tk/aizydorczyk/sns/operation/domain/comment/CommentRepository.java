package tk.aizydorczyk.sns.operation.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.aizydorczyk.sns.common.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

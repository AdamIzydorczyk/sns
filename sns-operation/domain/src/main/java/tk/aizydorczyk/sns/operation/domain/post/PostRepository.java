package tk.aizydorczyk.sns.operation.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.aizydorczyk.sns.common.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}

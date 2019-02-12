package tk.aizydorczyk.sns.search.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.aizydorczyk.sns.common.domain.post.Post;

public interface PostSearchRepository extends JpaRepository<Post, Long> {
}

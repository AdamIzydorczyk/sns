package tk.aizydorczyk.sns.search.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentSearchRepository extends JpaRepository<CommentSearch, Long> {
}

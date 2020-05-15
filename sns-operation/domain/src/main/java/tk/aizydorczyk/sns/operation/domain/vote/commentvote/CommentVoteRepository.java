package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {
    Optional<CommentVote> findByCommentIdAndCreatedBy(Long commentId, UUID createdBy);

    void deleteByCommentIdAndCreatedBy(Long commentId, UUID createdBy);
}

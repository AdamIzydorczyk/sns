package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {
    Optional<PostVote> findByPostIdAndCreatedBy(Long commentId, UUID createdBy);

    void deleteByPostIdAndCreatedBy(Long postId, UUID createdBy);
}

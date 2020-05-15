package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import org.hibernate.annotations.Where;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.Vote;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
@Where(clause = "deleted = false")
public class PostVote extends Vote<Post> {

    @ManyToOne
    private Post post;

    public PostVote() {
        super();
    }

    public PostVote(VoteDto dto,
                    Mapper mapper) {
        super(dto, mapper);
    }

    @Override
    public void applyParent(Post post) {
        this.post = post;
    }
}

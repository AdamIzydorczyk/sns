package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Function;

public class CreatePostVoteCommand extends BaseCreateDependentCommand<VoteDto, PostVote, Post> {
    protected CreatePostVoteCommand(Function<Long, Optional<Post>> findParentEntityById,
                                    Function<PostVote, PostVote> save,
                                    Function<PostVote, VoteDto> mapToDto,
                                    Mapper mapper) {
        super(findParentEntityById, save, mapToDto, mapper);
    }

    @Override
    protected PostVote createDependentEntity(VoteDto dto, Mapper mapper) {
        return new PostVote(dto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(VoteDto dto, Long parentId, AuditingInformation auditingInformation) {
        return new CreatePostVoteEvent(dto, parentId, auditingInformation);
    }
}

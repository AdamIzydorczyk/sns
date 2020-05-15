package tk.aizydorczyk.sns.operation.domain.vote.postvote.create;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteCommand;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.postvote.PostVote;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

class CreatePostVoteCommand extends BaseCreateVoteCommand<PostVote, Post> {
    protected CreatePostVoteCommand(BiFunction<Long, UUID, Optional<PostVote>> findByParentIdAndCreatedBy,
                                    Function<Long, Optional<Post>> findParentEntityById,
                                    Function<PostVote, PostVote> save,
                                    Function<PostVote, VoteDto> mapToDto,
                                    Mapper mapper) {
        super(findByParentIdAndCreatedBy, findParentEntityById, save, mapToDto, mapper);
    }

    @Override
    protected PostVote createVote(VoteDto dto,
                                  Mapper mapper) {
        return new PostVote(dto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(VoteDto dto,
                                    Long parentId,
                                    AuditingInformation auditingInformation) {
        return new CreatePostVoteEvent(dto, parentId, auditingInformation);
    }
}

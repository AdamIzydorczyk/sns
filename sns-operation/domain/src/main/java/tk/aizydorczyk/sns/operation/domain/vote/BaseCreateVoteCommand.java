package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteTypes;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class BaseCreateVoteCommand<VoteType extends Vote<ParentEntityType>,
        ParentEntityType extends BaseEntity<?>> {
    private final BiFunction<Long, UUID, Optional<VoteType>> findByParentIdAndCreatedBy;
    private final Function<Long, Optional<ParentEntityType>> findParentEntityById;
    private final Function<VoteType, VoteType> save;
    private final Function<VoteType, VoteDto> mapToVoteDto;
    private final Mapper mapper;

    public BaseCreateVoteCommand(BiFunction<Long, UUID, Optional<VoteType>> findByParentIdAndCreatedBy,
                                 Function<Long, Optional<ParentEntityType>> findParentEntityById,
                                 Function<VoteType, VoteType> save,
                                 Function<VoteType, VoteDto> mapToVoteDto,
                                 Mapper mapper) {
        this.findByParentIdAndCreatedBy = findByParentIdAndCreatedBy;
        this.findParentEntityById = findParentEntityById;
        this.save = save;
        this.mapToVoteDto = mapToVoteDto;
        this.mapper = mapper;
    }

    public VoteDto execute(Long parentId,
                           VoteDto dto,
                           AuditingInformation auditingInformation) {
        final ParentEntityType parent = findParentEntityById.apply(parentId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        final UUID userUuid = auditingInformation.getUserUuid();
        final Optional<VoteType> vote = findByParentIdAndCreatedBy.apply(parentId, userUuid);
        return vote.map(voteToChange -> changeType(voteToChange, dto.getType(), auditingInformation))
                .map(mapToVoteDto)
                .orElseGet(() -> createVote(dto, auditingInformation, parent));
    }

    public final VoteDto createVote(VoteDto voteDto,
                                    AuditingInformation auditingInformation,
                                    ParentEntityType parent) {
        final VoteType dependentEntity = createVote(voteDto, mapper);
        dependentEntity.applyParent(parent);
        dependentEntity.applyAuditingInformation(auditingInformation);
        save.apply(dependentEntity);
        return mapToVoteDto.apply(dependentEntity);
    }

    protected abstract VoteType createVote(VoteDto dto,
                                           Mapper mapper);

    private VoteType changeType(VoteType vote,
                                VoteTypes type, AuditingInformation auditingInformation) {
        vote.applyAuditingInformation(auditingInformation);
        vote.changeType(type);
        return vote;
    }

    public abstract SystemEvent prepareEvent(VoteDto dto, Long parentId, AuditingInformation auditingInformation);
}

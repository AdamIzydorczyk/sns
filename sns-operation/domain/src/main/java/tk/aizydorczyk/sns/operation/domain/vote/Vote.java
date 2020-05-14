package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteType;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Vote<EntityType extends BaseEntity> extends BaseDependentEntity<VoteDto, EntityType> {
    @Enumerated(value = EnumType.STRING)
    private VoteType type;

    public Vote() {
        super();
    }

    public Vote(VoteDto dto,
                Mapper mapper) {
        super(dto, mapper);
        this.type = dto.getType();
    }

    public VoteType getType() {
        return type;
    }

    @Override
    public void applyDto(VoteDto voteDto,
                         Mapper mapper) {
        this.type = voteDto.getType();
    }
}

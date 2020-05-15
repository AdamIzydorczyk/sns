package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.common.domain.vote.VoteTypes;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Vote<ParentEntityType extends BaseEntity<?>> extends BaseDependentEntity<VoteDto, ParentEntityType> {
    @Enumerated(value = EnumType.STRING)
    private VoteTypes type;

    public Vote() {
        super();
    }

    public Vote(VoteDto dto,
                Mapper mapper) {
        super(dto, mapper);
        this.type = dto.getType();
    }

    public VoteTypes getType() {
        return type;
    }

    @Override
    public void applyDto(VoteDto voteDto,
                         Mapper mapper) {
        this.type = voteDto.getType();
    }

    public void changeType(VoteTypes newType) {
        if (this.type != newType) {
            this.type = newType;
        }
    }
}

package tk.aizydorczyk.sns.common.infrastructure.jpa;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDependentEntity<TargetDtoType extends BaseDto, ParentType extends BaseEntity> extends BaseEntity<TargetDtoType> {

    protected BaseDependentEntity() {
        super();
    }

    protected BaseDependentEntity(TargetDtoType dto, Mapper mapper) {
        super(dto, mapper);
    }

    public abstract void applyParent(ParentType parentType);
}

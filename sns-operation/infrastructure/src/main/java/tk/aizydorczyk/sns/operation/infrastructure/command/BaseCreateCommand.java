package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.Objects;
import java.util.function.Function;

public abstract class BaseCreateCommand<DtoType extends BaseDto,
        EntityType extends BaseEntity> {

    private final Function<EntityType, EntityType> save;
    private final Function<EntityType, DtoType> mapToDto;
    private final Mapper mapper;

    protected BaseCreateCommand(Function<EntityType, EntityType> save,
                                Function<EntityType, DtoType> mapToDto,
                                Mapper mapper) {
        this.mapToDto = Objects.requireNonNull(mapToDto);
        this.save = Objects.requireNonNull(save);
        this.mapper = Objects.requireNonNull(mapper);
    }

    public final DtoType execute(DtoType postDto,
                                 AuditingInformation auditingInformation) {
        final EntityType entity = createEntity(postDto, mapper);
        entity.applyAuditingInformation(auditingInformation);
        save.apply(entity);
        return mapToDto.apply(entity);
    }

    protected abstract EntityType createEntity(DtoType postDto, Mapper mapper);
}

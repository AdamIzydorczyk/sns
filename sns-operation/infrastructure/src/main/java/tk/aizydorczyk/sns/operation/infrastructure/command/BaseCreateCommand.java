package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseCreateCommand<DtoType extends BaseDto,
        EntityType extends BaseEntity> {

    private final Function<EntityType, EntityType> save;
    private final Function<EntityType, DtoType> mapToDto;
    private final Mapper mapper;

    protected BaseCreateCommand(Function<EntityType, EntityType> save,
                                Function<EntityType, DtoType> mapToDto,
                                Mapper mapper) {
        this.mapToDto = mapToDto;
        this.save = save;
        this.mapper = mapper;
    }

    public final DtoType execute(DtoType postDto, LocalDateTime executionTime, UUID userUuid) {
        final EntityType entity = createEntity(postDto, mapper);
        entity.applyTimeAndUser(executionTime, userUuid);
        save.apply(entity);
        return mapToDto.apply(entity);
    }

    protected abstract EntityType createEntity(DtoType postDto, Mapper mapper);
}

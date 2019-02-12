package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseUpdateCommand<DtoType extends BaseDto, EntityType extends BaseEntity<DtoType>> {
    private final Function<Long, Optional<EntityType>> findById;
    private final Function<EntityType, DtoType> mapToPostDto;
    private final Mapper mapper;

    protected BaseUpdateCommand(Function<Long, Optional<EntityType>> findById,
                                Function<EntityType, DtoType> mapToPostDto, Mapper mapper) {
        this.findById = findById;
        this.mapToPostDto = mapToPostDto;
        this.mapper = mapper;
    }

    public final DtoType execute(Long id, DtoType postDto, LocalDateTime executionTime, UUID userUuid) {
        final EntityType entity = findById.apply(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));
        entity.applyDto(postDto, mapper);
        entity.applyTimeAndUser(executionTime, userUuid);
        return mapToPostDto.apply(entity);
    }
}

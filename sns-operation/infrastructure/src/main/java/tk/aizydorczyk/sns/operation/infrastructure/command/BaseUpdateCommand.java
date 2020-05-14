package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseUpdateCommand<DtoType extends BaseDto, EntityType extends BaseEntity<DtoType>> {
    private final Function<Long, Optional<EntityType>> findById;
    private final Function<EntityType, EntityType> save;
    private final Function<EntityType, DtoType> mapToPostDto;
    private final Mapper mapper;

    protected BaseUpdateCommand(Function<Long, Optional<EntityType>> findById,
                                Function<EntityType, EntityType> save,
                                Function<EntityType, DtoType> mapToPostDto,
                                Mapper mapper) {
        this.findById = Objects.requireNonNull(findById);
        this.save = Objects.requireNonNull(save);
        this.mapToPostDto = Objects.requireNonNull(mapToPostDto);
        this.mapper = Objects.requireNonNull(mapper);
    }

    public final DtoType execute(Long id, DtoType postDto,
                                 AuditingInformation auditingInformation) {
        final EntityType entity = findById.apply(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));
        entity.applyDto(postDto, mapper);
        entity.applyAuditingInformation(auditingInformation);
        save.apply(entity);
        return mapToPostDto.apply(entity);
    }

    public abstract SystemEvent prepareEvent(DtoType dto, Long id, AuditingInformation auditingInformation);
}

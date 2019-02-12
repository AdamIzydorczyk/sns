package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseCreateDependentCommand<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity> {

    private final Function<DependentEntityType, DependentEntityType> save;
    private final Function<DependentEntityType, DtoType> mapToDto;
    private final Function<Long, Optional<ParentEntityType>> findParentEntityById;
    private final Mapper mapper;

    protected BaseCreateDependentCommand(Function<Long, Optional<ParentEntityType>> findParentEntityById,
                                         Function<DependentEntityType, DependentEntityType> save,
                                         Function<DependentEntityType, DtoType> mapToDto,
                                         Mapper mapper) {
        this.mapToDto = mapToDto;
        this.save = save;
        this.findParentEntityById = findParentEntityById;
        this.mapper = mapper;
    }

    public final DtoType execute(Long postId, DtoType commentDto, LocalDateTime executionTime, UUID userUuid) {
        final ParentEntityType parent = findParentEntityById.apply(postId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        final DependentEntityType dependentEntity = createDependentEntity(commentDto, mapper);
        dependentEntity.applyParent(parent);
        dependentEntity.applyTimeAndUser(executionTime, userUuid);
        save.apply(dependentEntity);
        return mapToDto.apply(dependentEntity);
    }

    protected abstract DependentEntityType createDependentEntity(DtoType dto, Mapper mapper);
}

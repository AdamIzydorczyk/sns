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

public abstract class BaseUpdateDependentCommand<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntity>,
        ParentEntity extends BaseEntity> {

    private final Function<Long, Optional<DependentEntityType>> findDependentEntityById;
    private final Function<Long, Optional<ParentEntity>> findParentById;
    private final Function<DependentEntityType, DtoType> mapToDto;
    private final Mapper mapper;

    protected BaseUpdateDependentCommand(Function<Long, Optional<DependentEntityType>> findDependentEntityById,
                                         Function<Long, Optional<ParentEntity>> findParentById,
                                         Function<DependentEntityType, DtoType> mapToDto, Mapper mapper) {
        this.findDependentEntityById = findDependentEntityById;
        this.findParentById = findParentById;
        this.mapToDto = mapToDto;
        this.mapper = mapper;
    }

    public final DtoType execute(Long postId, Long commentId, DtoType dto, LocalDateTime executionTime, UUID userUuid) {
        findParentById.apply(postId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        final DependentEntityType dependentEntity = findDependentEntityById.apply(commentId)
                .orElseThrow(() -> new NoSuchElementException("Dependent entity not found"));

        dependentEntity.applyDto(dto, mapper);
        dependentEntity.applyTimeAndUser(executionTime, userUuid);
        return mapToDto.apply(dependentEntity);
    }
}

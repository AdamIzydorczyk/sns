package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
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
        this.findDependentEntityById = Objects.requireNonNull(findDependentEntityById);
        this.findParentById = Objects.requireNonNull(findParentById);
        this.mapToDto = Objects.requireNonNull(mapToDto);
        this.mapper = Objects.requireNonNull(mapper);
    }

    public final DtoType execute(Long postId,
                                 Long commentId,
                                 DtoType dto,
                                 AuditingInformation auditingInformation) {
        findParentById.apply(postId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        final DependentEntityType dependentEntity = findDependentEntityById.apply(commentId)
                .orElseThrow(() -> new NoSuchElementException("Dependent entity not found"));

        dependentEntity.applyDto(dto, mapper);
        dependentEntity.applyAuditingInformation(auditingInformation);
        return mapToDto.apply(dependentEntity);
    }
}

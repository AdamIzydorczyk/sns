package tk.aizydorczyk.sns.operation.infrastructure.command;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseCreateDependentCommand<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity<?>> {

    private final Function<DependentEntityType, DependentEntityType> save;
    private final Function<DependentEntityType, DtoType> mapToDto;
    private final Function<Long, Optional<ParentEntityType>> findParentEntityById;
    private final Mapper mapper;

    protected BaseCreateDependentCommand(Function<Long, Optional<ParentEntityType>> findParentEntityById,
                                         Function<DependentEntityType, DependentEntityType> save,
                                         Function<DependentEntityType, DtoType> mapToDto,
                                         Mapper mapper) {
        this.mapToDto = Objects.requireNonNull(mapToDto);
        this.save = Objects.requireNonNull(save);
        this.findParentEntityById = Objects.requireNonNull(findParentEntityById);
        this.mapper = Objects.requireNonNull(mapper);
    }

    public final DtoType execute(Long postId, DtoType commentDto,
                                 AuditingInformation auditingInformation) {
        final ParentEntityType parent = findParentEntityById.apply(postId)
                .orElseThrow(() -> new NoSuchElementException("Parent not found"));

        final DependentEntityType dependentEntity = createDependentEntity(commentDto, mapper);
        dependentEntity.applyParent(parent);
        dependentEntity.applyAuditingInformation(auditingInformation);
        save.apply(dependentEntity);
        return mapToDto.apply(dependentEntity);
    }

    protected abstract DependentEntityType createDependentEntity(DtoType dto, Mapper mapper);

    public abstract SystemEvent prepareEvent(DtoType dto, Long parentId, AuditingInformation auditingInformation);
}

package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.CreateDependentCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.DeleteDependentCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.UpdateDependentCommandEvent;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseDependentController<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity,
        CreateDependentEntityCommandType extends BaseCreateDependentCommand<DtoType, DependentEntityType, ParentEntityType>,
        UpdateDependentEntityCommandType extends BaseUpdateDependentCommand<DtoType, DependentEntityType, ParentEntityType>,
        DeleteDependentEntityCommandType extends BaseDeleteDependentCommand<ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private final CreateDependentEntityCommandType createDependentCommand;
    private final UpdateDependentEntityCommandType updateDependentCommand;
    private final DeleteDependentEntityCommandType deleteDependentCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    protected BaseDependentController(CreateDependentEntityCommandType createDependentCommand,
                                      UpdateDependentEntityCommandType updateDependentCommand,
                                      DeleteDependentEntityCommandType deleteDependentCommand,
                                      TransactionUtils transactionUtils,
                                      ApplicationEventPublisher eventPublisher,
                                      Clock clock) {
        this.createDependentCommand = createDependentCommand;
        this.updateDependentCommand = updateDependentCommand;
        this.deleteDependentCommand = deleteDependentCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
        this.clock = clock;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoType create(@PathVariable("parentId") Long parentId,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Create in: {} parentId: {} body: {}", getClass().getSimpleName(), parentId, dto);
        return transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new CreateDependentCommandEvent<>(createDependentCommand.getClass(),
                    dto.getClass(),
                    dto,
                    parentId,
                    executionTime,
                    userUuid));
            return createDependentCommand.execute(parentId, dto, executionTime, userUuid);
        });
    }

    @PutMapping("/{dependentId}")
    public DtoType update(@PathVariable("parentId") Long parentId,
                          @PathVariable("dependentId") Long dependentId,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Update in: {} parentId: {} dependentId: {} body: {}", getClass().getSimpleName(), parentId, dependentId, dto);
        return transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new UpdateDependentCommandEvent<>(updateDependentCommand.getClass(),
                    dto.getClass(),
                    dto,
                    parentId,
                    dependentId,
                    executionTime,
                    userUuid));
            return updateDependentCommand.execute(parentId, dependentId, dto, executionTime, userUuid);
        });
    }

    @DeleteMapping("/{dependentId}")
    public void delete(@PathVariable("parentId") Long parentId,
                       @PathVariable("dependentId") Long dependentId,
                       @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Delete in: {} parentId: {} dependentId: {}", getClass().getSimpleName(), parentId, dependentId);
        transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new DeleteDependentCommandEvent(deleteDependentCommand.getClass(),
                    parentId,
                    dependentId,
                    executionTime,
                    userUuid));
            deleteDependentCommand.execute(parentId, dependentId);
        });
    }
}

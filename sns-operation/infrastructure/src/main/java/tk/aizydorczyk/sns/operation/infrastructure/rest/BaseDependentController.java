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
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseDependentController<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity<?>,
        CreateDependentEntityCommandType extends BaseCreateDependentCommand<DtoType, DependentEntityType, ParentEntityType>,
        UpdateDependentEntityCommandType extends BaseUpdateDependentCommand<DtoType, DependentEntityType, ParentEntityType>,
        DeleteDependentEntityCommandType extends BaseDeleteDependentCommand<ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private final CreateDependentEntityCommandType createDependentCommand;
    private final UpdateDependentEntityCommandType updateDependentCommand;
    private final DeleteDependentEntityCommandType deleteDependentCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    protected BaseDependentController(CreateDependentEntityCommandType createDependentCommand,
                                      UpdateDependentEntityCommandType updateDependentCommand,
                                      DeleteDependentEntityCommandType deleteDependentCommand,
                                      TransactionUtils transactionUtils,
                                      ApplicationEventPublisher eventPublisher) {
        this.createDependentCommand = createDependentCommand;
        this.updateDependentCommand = updateDependentCommand;
        this.deleteDependentCommand = deleteDependentCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoType create(@PathVariable("parentId") Long parentId,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Create in: {} parentId: {} body: {}", getClass().getSimpleName(), parentId, dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(createDependentCommand.prepareEvent(dto,
                    parentId,
                    auditingInformation));
            return createDependentCommand.execute(parentId, dto, auditingInformation);
        });
    }

    @PutMapping("/{dependentId}")
    public DtoType update(@PathVariable("parentId") Long parentId,
                          @PathVariable("dependentId") Long dependentId,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Update in: {} parentId: {} dependentId: {} body: {}", getClass().getSimpleName(), parentId, dependentId, dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(updateDependentCommand.prepareEvent(
                    dto,
                    parentId,
                    dependentId,
                    auditingInformation));
            return updateDependentCommand.execute(parentId, dependentId, dto, auditingInformation);
        });
    }

    @DeleteMapping("/{dependentId}")
    public void delete(@PathVariable("parentId") Long parentId,
                       @PathVariable("dependentId") Long dependentId,
                       @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Delete in: {} parentId: {} dependentId: {}", getClass().getSimpleName(), parentId, dependentId);
        transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(deleteDependentCommand.prepareEvent(
                    parentId,
                    dependentId,
                    auditingInformation));
            deleteDependentCommand.execute(parentId, dependentId);
        });
    }
}

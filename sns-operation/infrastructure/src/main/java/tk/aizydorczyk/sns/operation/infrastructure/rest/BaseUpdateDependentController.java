package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseUpdateDependentController<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity<?>,
        UpdateDependentEntityCommandType extends BaseUpdateDependentCommand<DtoType, DependentEntityType, ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUpdateDependentController.class);

    private final UpdateDependentEntityCommandType updateDependentCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseUpdateDependentController(UpdateDependentEntityCommandType updateDependentCommand,
                                         TransactionUtils transactionUtils,
                                         ApplicationEventPublisher eventPublisher) {
        this.updateDependentCommand = updateDependentCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
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

}

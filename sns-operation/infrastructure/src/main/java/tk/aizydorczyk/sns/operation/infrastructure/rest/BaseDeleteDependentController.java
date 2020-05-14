package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

public abstract class BaseDeleteDependentController<ParentEntityType extends BaseEntity<?>,
        DeleteDependentEntityCommandType extends BaseDeleteDependentCommand<ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDeleteDependentController.class);

    private final DeleteDependentEntityCommandType deleteDependentCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseDeleteDependentController(DeleteDependentEntityCommandType deleteDependentCommand,
                                         TransactionUtils transactionUtils,
                                         ApplicationEventPublisher eventPublisher) {
        this.deleteDependentCommand = deleteDependentCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
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

package tk.aizydorczyk.sns.operation.domain.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDeleteDependentController;

public abstract class BaseDeleteVoteController<ParentEntityType extends BaseEntity<?>,
        DeleteVoteCommandType extends BaseDeleteVoteCommand<ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDeleteDependentController.class);

    private final DeleteVoteCommandType deleteVoteCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseDeleteVoteController(DeleteVoteCommandType deleteVoteCommand,
                                    TransactionUtils transactionUtils,
                                    ApplicationEventPublisher eventPublisher) {
        this.deleteVoteCommand = deleteVoteCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("parentId") Long parentId,
                       @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Delete in: {} parentId: {}", getClass().getSimpleName(), parentId);
        transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(deleteVoteCommand.prepareEvent(
                    parentId,
                    auditingInformation));
            deleteVoteCommand.execute(parentId, auditingInformation.getUserUuid());
        });
    }
}
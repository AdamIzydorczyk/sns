package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;

public abstract class BaseDeleteController<DeleteCommand extends BaseDeleteCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDeleteController.class);

    private final DeleteCommand deleteCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseDeleteController(DeleteCommand deleteCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        this.deleteCommand = deleteCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id,
                       @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Delete in: {} id: {}", getClass().getSimpleName(), id);
        transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(deleteCommand.prepareEvent(id, auditingInformation));
            deleteCommand.execute(id);
        });
    }

}
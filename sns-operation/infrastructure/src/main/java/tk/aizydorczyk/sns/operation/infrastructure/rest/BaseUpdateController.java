package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseUpdateController<DtoType extends BaseDto,
        EntityType extends BaseEntity<DtoType>,
        UpdateCommand extends BaseUpdateCommand<DtoType, EntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUpdateController.class);

    private final UpdateCommand updateCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseUpdateController(UpdateCommand updateCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        this.updateCommand = updateCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @PutMapping("/{id}")
    public DtoType update(@PathVariable("id") Long id,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Update in: {} id: {} body: {}", getClass().getSimpleName(), id, dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(updateCommand.prepareEvent(dto, id, auditingInformation));
            return updateCommand.execute(id, dto, auditingInformation);
        });
    }

}
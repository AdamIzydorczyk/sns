package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseCreateController<DtoType extends BaseDto,
        EntityType extends BaseEntity<DtoType>,
        CreateCommand extends BaseCreateCommand<DtoType, EntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCreateController.class);

    private final CreateCommand createCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseCreateController(CreateCommand createCommand, TransactionUtils transactionUtils, ApplicationEventPublisher eventPublisher) {
        this.createCommand = createCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoType create(@RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Create in: {} body: {}", getClass().getSimpleName(), dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(createCommand.prepareEvent(dto, auditingInformation));
            return createCommand.execute(dto, auditingInformation);
        });
    }

}

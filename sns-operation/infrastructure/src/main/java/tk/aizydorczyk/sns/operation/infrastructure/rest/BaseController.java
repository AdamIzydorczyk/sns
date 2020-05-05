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
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.CreateCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.DeleteCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.UpdateCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseController<DtoType extends BaseDto,
        EntityType extends BaseEntity<DtoType>,
        CreateCommand extends BaseCreateCommand<DtoType, EntityType>,
        UpdateCommand extends BaseUpdateCommand<DtoType, EntityType>,
        DeleteCommand extends BaseDeleteCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private final CreateCommand createCommand;
    private final UpdateCommand updateCommand;
    private final DeleteCommand deleteCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    protected BaseController(CreateCommand createCommand,
                             UpdateCommand updateCommand,
                             DeleteCommand deleteCommand,
                             TransactionUtils transactionUtils,
                             ApplicationEventPublisher eventPublisher) {
        this.createCommand = createCommand;
        this.updateCommand = updateCommand;
        this.deleteCommand = deleteCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoType create(@RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Create in: {} body: {}", getClass().getSimpleName(), dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(new CreateCommandEvent<>(createCommand.getClass(),
                    dto.getClass(),
                    dto,
                    auditingInformation));
            return createCommand.execute(dto, auditingInformation);
        });
    }

    @PutMapping("/{id}")
    public DtoType update(@PathVariable("id") Long id,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Update in: {} id: {} body: {}", getClass().getSimpleName(), id, dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(new UpdateCommandEvent<>(updateCommand.getClass(), dto.getClass(), dto, id, auditingInformation));
            return updateCommand.execute(id, dto, auditingInformation);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,
                       @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Delete in: {} id: {}", getClass().getSimpleName(), id);
        transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(new DeleteCommandEvent(deleteCommand.getClass(), id, auditingInformation));
            deleteCommand.execute(id);
        });
    }
}

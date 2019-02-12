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
import tk.aizydorczyk.sns.common.infrastructure.jpa.BaseEntity;
import tk.aizydorczyk.sns.common.infrastructure.rest.BaseDto;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.CreateCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.DeleteCommandEvent;
import tk.aizydorczyk.sns.operation.infrastructure.event.UpdateCommandEvent;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private final Clock clock;

    protected BaseController(CreateCommand createCommand,
                             UpdateCommand updateCommand,
                             DeleteCommand deleteCommand,
                             TransactionUtils transactionUtils,
                             ApplicationEventPublisher eventPublisher,
                             Clock clock) {
        this.createCommand = createCommand;
        this.updateCommand = updateCommand;
        this.deleteCommand = deleteCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
        this.clock = clock;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoType create(@RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Create in: " + getClass().getSimpleName() + " body: " + dto);
        return transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new CreateCommandEvent<>(createCommand.getClass(),
                    dto.getClass(),
                    dto,
                    executionTime,
                    userUuid));
            return createCommand.execute(dto, executionTime, userUuid);
        });
    }

    @PutMapping("/{id}")
    public DtoType update(@PathVariable("id") Long id,
                          @RequestBody @Valid DtoType dto,
                          @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Update in: " + getClass().getSimpleName() + "id: " + id + " body: " + dto);

        return transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new UpdateCommandEvent<>(updateCommand.getClass(), dto.getClass(), dto, id, executionTime, userUuid));
            return updateCommand.execute(id, dto, executionTime, userUuid);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,
                       @RequestHeader(value = "userUuid") UUID userUuid) {
        LOGGER.info("Delete in: " + getClass().getSimpleName() + " id: " + id);
        transactionUtils.runInTransaction(() -> {
            final LocalDateTime executionTime = LocalDateTime.now(clock);
            eventPublisher.publishEvent(new DeleteCommandEvent(deleteCommand.getClass(), id, executionTime, userUuid));
            deleteCommand.execute(id);
        });
    }
}

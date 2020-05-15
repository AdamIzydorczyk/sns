package tk.aizydorczyk.sns.operation.domain.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import javax.validation.Valid;


public abstract class BaseCreateVoteController<CreateVoteCommandType extends BaseCreateVoteCommand<?, ?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCreateVoteController.class);

    private final CreateVoteCommandType createVoteCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    public BaseCreateVoteController(CreateVoteCommandType createVoteCommand,
                                    TransactionUtils transactionUtils,
                                    ApplicationEventPublisher eventPublisher) {
        this.createVoteCommand = createVoteCommand;
        this.transactionUtils = transactionUtils;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto create(@PathVariable("parentId") Long parentId,
                          @RequestBody @Valid VoteDto dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        LOGGER.info("Create in: {} parentId: {} body: {}", getClass().getSimpleName(), parentId, dto);
        return transactionUtils.runInTransaction(() -> {
            eventPublisher.publishEvent(createVoteCommand.prepareEvent(dto,
                    parentId,
                    auditingInformation));
            return createVoteCommand.execute(parentId, dto, auditingInformation);
        });
    }
}

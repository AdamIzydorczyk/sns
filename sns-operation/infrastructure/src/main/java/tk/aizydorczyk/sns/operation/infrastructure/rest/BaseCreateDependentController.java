package tk.aizydorczyk.sns.operation.infrastructure.rest;

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
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseDependentEntity;
import tk.aizydorczyk.sns.operation.infrastructure.jpa.BaseEntity;

import javax.validation.Valid;

public abstract class BaseCreateDependentController<DtoType extends BaseDto,
        DependentEntityType extends BaseDependentEntity<DtoType, ParentEntityType>,
        ParentEntityType extends BaseEntity<?>,
        CreateDependentEntityCommandType extends BaseCreateDependentCommand<DtoType, DependentEntityType, ParentEntityType>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCreateDependentController.class);

    private final CreateDependentEntityCommandType createDependentCommand;

    private final TransactionUtils transactionUtils;
    private final ApplicationEventPublisher eventPublisher;

    protected BaseCreateDependentController(CreateDependentEntityCommandType createDependentCommand,
                                            TransactionUtils transactionUtils,
                                            ApplicationEventPublisher eventPublisher) {
        this.createDependentCommand = createDependentCommand;
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

}

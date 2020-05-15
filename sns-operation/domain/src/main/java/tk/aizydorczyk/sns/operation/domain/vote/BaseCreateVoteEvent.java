package tk.aizydorczyk.sns.operation.domain.vote;

import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public abstract class BaseCreateVoteEvent<CreateVoteCommandType extends BaseCreateVoteCommand<?, ?>> implements SystemEvent {
    private final Class<CreateVoteCommandType> commandClass;
    private final Class<VoteDto> dtoClass;
    private final VoteDto dto;
    private final Long parentId;
    private final AuditingInformation auditingInformation;

    public BaseCreateVoteEvent(Class<CreateVoteCommandType> commandClass,
                               VoteDto dto,
                               Long parentId,
                               AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.dto = Objects.requireNonNull(dto);
        this.dtoClass = (Class<VoteDto>) dto.getClass();
        this.parentId = Objects.requireNonNull(parentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<CreateVoteCommandType> getCommandClass() {
        return commandClass;
    }

    public Class<VoteDto> getDtoClass() {
        return dtoClass;
    }

    public VoteDto getDto() {
        return dto;
    }

    public Long getParentId() {
        return parentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }
}

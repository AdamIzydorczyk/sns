package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Objects;

public class CreateCommentVoteEvent implements SystemEvent {
    private final Class<? extends CreateCommentVoteCommand> commandClass;
    private final Class<VoteDto> dtoClass;
    private final VoteDto dto;
    private final Long parentId;
    private final AuditingInformation auditingInformation;

    public CreateCommentVoteEvent(Class<? extends CreateCommentVoteCommand> commandClass,
                                  VoteDto dto,
                                  Long parentId,
                                  AuditingInformation auditingInformation) {
        this.commandClass = Objects.requireNonNull(commandClass);
        this.dto = Objects.requireNonNull(dto);
        this.dtoClass = (Class<VoteDto>) dto.getClass();
        this.parentId = Objects.requireNonNull(parentId);
        this.auditingInformation = Objects.requireNonNull(auditingInformation);
    }

    public Class<? extends CreateCommentVoteCommand> getCommandClass() {
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

package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.operation.infrastructure.event.BaseUpdateEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class UpdatePostEvent extends BaseUpdateEvent<PostDto, UpdatePostCommand> {
    public UpdatePostEvent(PostDto dto, Long id, AuditingInformation auditingInformation) {
        super(UpdatePostCommand.class, PostDto.class, dto, id, auditingInformation);
    }
}

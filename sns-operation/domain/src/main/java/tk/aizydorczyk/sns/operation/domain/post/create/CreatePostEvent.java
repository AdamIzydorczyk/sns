package tk.aizydorczyk.sns.operation.domain.post.create;

import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseCreateEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

public class CreatePostEvent extends BaseCreateEvent<PostDto, CreatePostCommand> {
    public CreatePostEvent(PostDto dto,
                           AuditingInformation auditingInformation) {
        super(CreatePostCommand.class, PostDto.class, dto, auditingInformation);
    }
}

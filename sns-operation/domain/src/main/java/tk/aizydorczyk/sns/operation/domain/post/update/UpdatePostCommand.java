package tk.aizydorczyk.sns.operation.domain.post.update;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Function;

class UpdatePostCommand extends BaseUpdateCommand<PostDto, Post> {
    UpdatePostCommand(Function<Long, Optional<Post>> findById,
                      Function<Post, Post> save,
                      Function<Post, PostDto> mapToPostDto,
                      Mapper mapper) {
        super(findById, save, mapToPostDto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(PostDto dto, Long id, AuditingInformation auditingInformation) {
        return new UpdatePostEvent(dto, id, auditingInformation);
    }
}

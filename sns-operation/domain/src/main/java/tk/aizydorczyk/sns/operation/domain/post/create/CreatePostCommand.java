package tk.aizydorczyk.sns.operation.domain.post.create;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.function.Function;

class CreatePostCommand extends BaseCreateCommand<PostDto, Post> {
    CreatePostCommand(Function<Post, Post> save,
                      Function<Post, PostDto> mapToDto,
                      Mapper mapper) {
        super(save, mapToDto, mapper);
    }

    @Override
    protected Post createEntity(PostDto postDto, Mapper mapper) {
        return new Post(postDto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(PostDto dto, AuditingInformation auditingInformation) {
        return new CreatePostEvent(dto, auditingInformation);
    }
}

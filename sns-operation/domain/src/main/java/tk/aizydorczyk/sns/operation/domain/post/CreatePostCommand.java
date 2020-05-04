package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateCommand;

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
}

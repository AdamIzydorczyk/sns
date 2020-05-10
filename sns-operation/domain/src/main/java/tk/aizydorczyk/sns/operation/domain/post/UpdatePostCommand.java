package tk.aizydorczyk.sns.operation.domain.post;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateCommand;

import java.util.Optional;
import java.util.function.Function;

class UpdatePostCommand extends BaseUpdateCommand<PostDto, Post> {
    UpdatePostCommand(Function<Long, Optional<Post>> findById,
                      Function<Post, Post> save,
                      Function<Post, PostDto> mapToPostDto,
                      Mapper mapper) {
        super(findById, save, mapToPostDto, mapper);
    }
}

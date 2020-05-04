package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;

import java.util.Optional;
import java.util.function.Function;

class UpdateCommentCommand extends BaseUpdateDependentCommand<CommentDto, Comment, Post> {
    UpdateCommentCommand(Function<Long, Optional<Comment>> findCommentById,
                         Function<Long, Optional<Post>> findPostById,
                         Function<Comment, CommentDto> mapToPostDto,
                         Mapper mapper) {
        super(findCommentById, findPostById, mapToPostDto, mapper);
    }
}

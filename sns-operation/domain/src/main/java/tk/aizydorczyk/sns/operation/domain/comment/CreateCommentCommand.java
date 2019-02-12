package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.domain.comment.Comment;
import tk.aizydorczyk.sns.common.domain.comment.CommentDto;
import tk.aizydorczyk.sns.common.domain.post.Post;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;

import java.util.Optional;
import java.util.function.Function;

public class CreateCommentCommand extends BaseCreateDependentCommand<CommentDto, Comment, Post> {
    CreateCommentCommand(Function<Long, Optional<Post>> findPostById,
                         Function<Comment, Comment> saveComment,
                         Function<Comment, CommentDto> mapToCommentDto,
                         Mapper mapper) {
        super(findPostById, saveComment, mapToCommentDto, mapper);
    }

    @Override
    protected Comment createDependentEntity(CommentDto commentDto, Mapper mapper) {
        return new Comment(commentDto, mapper);
    }

}

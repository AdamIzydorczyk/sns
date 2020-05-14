package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

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

    @Override
    public SystemEvent prepareEvent(CommentDto dto, Long parentId, AuditingInformation auditingInformation) {
        return new CreateCommentEvent(dto, parentId, auditingInformation);
    }

}

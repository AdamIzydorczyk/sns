package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Function;

class UpdateCommentCommand extends BaseUpdateDependentCommand<CommentDto, Comment, Post> {
    UpdateCommentCommand(Function<Long, Optional<Comment>> findCommentById,
                         Function<Long, Optional<Post>> findPostById,
                         Function<Comment, Comment> save,
                         Function<Comment, CommentDto> mapToPostDto,
                         Mapper mapper) {
        super(findCommentById, findPostById, save, mapToPostDto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(CommentDto dto,
                                    Long parentId,
                                    Long dependentId,
                                    AuditingInformation auditingInformation) {
        return new UpdateCommentEvent(dto, parentId, dependentId, auditingInformation);
    }
}

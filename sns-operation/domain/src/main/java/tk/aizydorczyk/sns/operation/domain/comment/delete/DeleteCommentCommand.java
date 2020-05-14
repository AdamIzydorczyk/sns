package tk.aizydorczyk.sns.operation.domain.comment.delete;

import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

class DeleteCommentCommand extends BaseDeleteDependentCommand<Post> {

    DeleteCommentCommand(Consumer<Long> deleteById,
                         Function<Long, Optional<Post>> findPostById) {
        super(deleteById, findPostById);
    }

    @Override
    public SystemEvent prepareEvent(Long parentId,
                                    Long dependentId,
                                    AuditingInformation auditingInformation) {
        return new DeleteCommentEvent(parentId, dependentId, auditingInformation);
    }
}

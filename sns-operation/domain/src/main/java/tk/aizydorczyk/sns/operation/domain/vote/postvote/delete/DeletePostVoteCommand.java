package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

class DeletePostVoteCommand extends BaseDeleteDependentCommand<Post> {
    protected DeletePostVoteCommand(Consumer<Long> deleteById,
                                    Function<Long, Optional<Post>> findParentById) {
        super(deleteById, findParentById);
    }

    @Override
    public SystemEvent prepareEvent(Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        return new DeletePostVoteEvent(parentId, dependentId, auditingInformation);
    }
}

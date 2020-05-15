package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.BaseDeleteVoteCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

class DeletePostVoteCommand extends BaseDeleteVoteCommand<Post> {
    protected DeletePostVoteCommand(BiConsumer<Long, UUID> deleteByPostIdAndCreatedBy,
                                    Function<Long, Optional<Post>> findParentById) {
        super(deleteByPostIdAndCreatedBy, findParentById);
    }

    @Override
    public SystemEvent prepareEvent(Long parentId,
                                    AuditingInformation auditingInformation) {
        return new DeletePostVoteEvent(parentId, auditingInformation);
    }
}

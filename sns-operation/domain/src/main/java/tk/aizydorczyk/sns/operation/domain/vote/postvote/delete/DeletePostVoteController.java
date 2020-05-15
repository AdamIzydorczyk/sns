package tk.aizydorczyk.sns.operation.domain.vote.postvote.delete;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.BaseDeleteVoteController;

@RestController
@RequestMapping(value = "posts/{parentId}/votes")
class DeletePostVoteController extends BaseDeleteVoteController<Post, DeletePostVoteCommand> {
    public DeletePostVoteController(DeletePostVoteCommand deleteDependentCommand,
                                    TransactionUtils transactionUtils,
                                    ApplicationEventPublisher eventPublisher) {
        super(deleteDependentCommand, transactionUtils, eventPublisher);
    }
}

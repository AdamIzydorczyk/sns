package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteController;

@RestController
@RequestMapping(value = "/posts/{postId}/comments/{parentId}/votes")
public class CreateCommentVoteController extends BaseCreateVoteController<CreateCommentVoteCommand> {
    CreateCommentVoteController(CreateCommentVoteCommand createDependentCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        super(createDependentCommand, transactionUtils, eventPublisher);
    }
}

package tk.aizydorczyk.sns.operation.domain.vote.postvote.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteController;

@RestController
@RequestMapping(value = "posts/{parentId}/votes")
class CreatePostVoteController extends BaseCreateVoteController<CreatePostVoteCommand> {
    public CreatePostVoteController(CreatePostVoteCommand createVoteCommand,
                                    TransactionUtils transactionUtils,
                                    ApplicationEventPublisher eventPublisher) {
        super(createVoteCommand, transactionUtils, eventPublisher);
    }
}

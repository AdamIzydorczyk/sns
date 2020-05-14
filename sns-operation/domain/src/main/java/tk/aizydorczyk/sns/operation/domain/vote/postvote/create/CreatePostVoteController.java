package tk.aizydorczyk.sns.operation.domain.vote.postvote.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.postvote.PostVote;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseCreateDependentController;

@RestController
@RequestMapping(value = "posts/{parentId}/votes")
class CreatePostVoteController extends BaseCreateDependentController<VoteDto, PostVote, Post, CreatePostVoteCommand> {
    protected CreatePostVoteController(CreatePostVoteCommand createDependentCommand,
                                       TransactionUtils transactionUtils,
                                       ApplicationEventPublisher eventPublisher) {
        super(createDependentCommand, transactionUtils, eventPublisher);
    }
}

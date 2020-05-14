package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.commentvote.CommentVote;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseCreateDependentController;

@RestController
@RequestMapping(value = "/posts/{postId}/comments/{parentId}/votes")
class CreateCommentVoteController extends BaseCreateDependentController<VoteDto, CommentVote, Comment, CreateCommentVoteCommand> {
    protected CreateCommentVoteController(CreateCommentVoteCommand createDependentCommand,
                                          TransactionUtils transactionUtils,
                                          ApplicationEventPublisher eventPublisher) {
        super(createDependentCommand, transactionUtils, eventPublisher);
    }
}

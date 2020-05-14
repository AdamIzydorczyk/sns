package tk.aizydorczyk.sns.operation.domain.vote.commentvote.delete;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDeleteDependentController;

@RestController
@RequestMapping(value = "/posts/{postId}/comments/{parentId}/votes")
class DeleteCommentVoteController extends BaseDeleteDependentController<Comment, DeleteCommentVoteCommand> {
    public DeleteCommentVoteController(DeleteCommentVoteCommand deleteDependentCommand,
                                       TransactionUtils transactionUtils,
                                       ApplicationEventPublisher eventPublisher) {
        super(deleteDependentCommand, transactionUtils, eventPublisher);
    }
}

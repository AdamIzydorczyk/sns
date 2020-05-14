package tk.aizydorczyk.sns.operation.domain.comment.delete;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDeleteDependentController;

@RestController
@RequestMapping(value = "posts/{parentId}/comments")
class DeleteCommentController extends BaseDeleteDependentController<Post, DeleteCommentCommand> {
    public DeleteCommentController(DeleteCommentCommand deleteDependentCommand,
                                   TransactionUtils transactionUtils,
                                   ApplicationEventPublisher eventPublisher) {
        super(deleteDependentCommand, transactionUtils, eventPublisher);
    }
}

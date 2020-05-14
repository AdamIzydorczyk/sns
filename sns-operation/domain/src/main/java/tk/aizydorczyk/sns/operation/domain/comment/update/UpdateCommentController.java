package tk.aizydorczyk.sns.operation.domain.comment.update;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.comment.CommentDto;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseUpdateDependentController;

@RestController
@RequestMapping(value = "posts/{parentId}/comments")
class UpdateCommentController extends BaseUpdateDependentController<CommentDto, Comment, Post, UpdateCommentCommand> {
    public UpdateCommentController(UpdateCommentCommand updateDependentCommand,
                                   TransactionUtils transactionUtils,
                                   ApplicationEventPublisher eventPublisher) {
        super(updateDependentCommand, transactionUtils, eventPublisher);
    }
}

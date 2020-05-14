package tk.aizydorczyk.sns.operation.domain.comment.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.comment.CommentDto;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseCreateDependentController;

@RestController
@RequestMapping(value = "posts/{parentId}/comments")
class CreateCommentController extends BaseCreateDependentController<CommentDto, Comment, Post, CreateCommentCommand> {
    protected CreateCommentController(CreateCommentCommand createDependentCommand,
                                      TransactionUtils transactionUtils,
                                      ApplicationEventPublisher eventPublisher) {
        super(createDependentCommand, transactionUtils, eventPublisher);
    }
}

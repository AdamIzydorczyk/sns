package tk.aizydorczyk.sns.operation.domain.comment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.domain.comment.Comment;
import tk.aizydorczyk.sns.common.domain.comment.CommentDto;
import tk.aizydorczyk.sns.common.domain.post.Post;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDependentController;

import java.time.Clock;

@RestController
@RequestMapping(value = "posts/{parentId}/comments")
class CommentController extends BaseDependentController<CommentDto, Comment, Post, CreateCommentCommand, UpdateCommentCommand, DeleteCommentCommand> {
    public CommentController(CreateCommentCommand createCommentCommand,
                             UpdateCommentCommand updateCommentCommand,
                             DeleteCommentCommand deletePostCommand,
                             TransactionUtils transactionUtils,
                             ApplicationEventPublisher eventPublisher,
                             Clock clock) {
        super(createCommentCommand,
                updateCommentCommand,
                deletePostCommand,
                transactionUtils,
                eventPublisher,
                clock);
    }
}

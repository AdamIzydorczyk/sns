package tk.aizydorczyk.sns.operation.domain.post;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseController;

@RestController
@RequestMapping(value = "posts")
class PostController extends BaseController<PostDto, Post, CreatePostCommand, UpdatePostCommand, DeletePostCommand> {
    public PostController(CreatePostCommand createPostCommand,
                          UpdatePostCommand updatePostCommand,
                          DeletePostCommand deletePostCommand,
                          TransactionUtils transactionUtils,
                          ApplicationEventPublisher eventPublisher) {
        super(createPostCommand,
                updatePostCommand,
                deletePostCommand,
                transactionUtils,
                eventPublisher);
    }
}

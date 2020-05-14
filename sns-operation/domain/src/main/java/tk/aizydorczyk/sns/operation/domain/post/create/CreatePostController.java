package tk.aizydorczyk.sns.operation.domain.post.create;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseCreateController;

@RestController
@RequestMapping(value = "posts")
class CreatePostController extends BaseCreateController<PostDto, Post, CreatePostCommand> {
    public CreatePostController(CreatePostCommand createCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        super(createCommand, transactionUtils, eventPublisher);
    }
}

package tk.aizydorczyk.sns.operation.domain.post.update;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.post.PostDto;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseUpdateController;

@RestController
@RequestMapping(value = "posts")
class UpdatePostController extends BaseUpdateController<PostDto, Post, UpdatePostCommand> {
    public UpdatePostController(UpdatePostCommand updateCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        super(updateCommand, transactionUtils, eventPublisher);
    }
}

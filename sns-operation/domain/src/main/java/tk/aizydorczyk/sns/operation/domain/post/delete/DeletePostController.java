package tk.aizydorczyk.sns.operation.domain.post.delete;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDeleteController;

@RestController
@RequestMapping(value = "posts")
class DeletePostController extends BaseDeleteController<DeletePostCommand> {
    public DeletePostController(DeletePostCommand deleteCommand,
                                TransactionUtils transactionUtils,
                                ApplicationEventPublisher eventPublisher) {
        super(deleteCommand, transactionUtils, eventPublisher);
    }
}

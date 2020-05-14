package tk.aizydorczyk.sns.operation.domain.comment.delete;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.operation.infrastructure.event.BaseDeleteDependentEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

@RestController
@RequestMapping(value = "posts/{parentId}/comments")
class DeleteCommentController extends BaseDeleteDependentEvent<DeleteCommentCommand> {
    public DeleteCommentController(Class<DeleteCommentCommand> commandClass,
                                   Long parentId,
                                   Long dependentId,
                                   AuditingInformation auditingInformation) {
        super(commandClass, parentId, dependentId, auditingInformation);
    }
}

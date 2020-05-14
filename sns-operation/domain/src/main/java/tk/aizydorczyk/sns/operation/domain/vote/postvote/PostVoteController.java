package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.aizydorczyk.sns.common.infrastructure.utils.TransactionUtils;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDependentController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "posts/{parentId}/votes")
public class PostVoteController extends BaseDependentController<VoteDto, PostVote, Post, CreatePostVoteCommand, UpdatePostVoteCommand, DeletePostVoteCommand> {
    protected PostVoteController(CreatePostVoteCommand createDependentCommand,
                                 UpdatePostVoteCommand updateDependentCommand,
                                 DeletePostVoteCommand deleteDependentCommand,
                                 TransactionUtils transactionUtils,
                                 ApplicationEventPublisher eventPublisher) {
        super(createDependentCommand, updateDependentCommand, deleteDependentCommand, transactionUtils, eventPublisher);
    }

    @PutMapping("/{dependentId}")
    @Override
    public VoteDto update(@PathVariable("parentId") Long parentId,
                          @PathVariable("dependentId") Long dependentId,
                          @RequestBody @Valid VoteDto dto,
                          @RequestHeader(value = "userUuid") AuditingInformation auditingInformation) {
        throw new AssertionError("Method not allowed");
    }
}

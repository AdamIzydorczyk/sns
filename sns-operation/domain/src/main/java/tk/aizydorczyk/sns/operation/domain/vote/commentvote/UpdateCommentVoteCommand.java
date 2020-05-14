package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Function;

public class UpdateCommentVoteCommand extends BaseUpdateDependentCommand<VoteDto, CommentVote, Comment> {
    protected UpdateCommentVoteCommand(Function<Long, Optional<CommentVote>> findDependentEntityById,
                                       Function<Long, Optional<Comment>> findParentById,
                                       Function<CommentVote, CommentVote> save,
                                       Function<CommentVote, VoteDto> mapToDto,
                                       Mapper mapper) {
        super(findDependentEntityById, findParentById, save, mapToDto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(VoteDto dto, Long parentId, Long dependentId, AuditingInformation auditingInformation) {
        return new UpdateCommentVoteEvent(dto, parentId, dependentId, auditingInformation);
    }
}

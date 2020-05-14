package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.commentvote.CommentVote;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;
import tk.aizydorczyk.sns.operation.infrastructure.event.SystemEvent;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.function.Function;

class CreateCommentVoteCommand extends BaseCreateDependentCommand<VoteDto, CommentVote, Comment> {
    protected CreateCommentVoteCommand(Function<Long, Optional<Comment>> findParentEntityById,
                                       Function<CommentVote, CommentVote> save,
                                       Function<CommentVote, VoteDto> mapToDto,
                                       Mapper mapper) {
        super(findParentEntityById, save, mapToDto, mapper);
    }

    @Override
    protected CommentVote createDependentEntity(VoteDto dto,
                                                Mapper mapper) {
        return new CommentVote(dto, mapper);
    }

    @Override
    public SystemEvent prepareEvent(VoteDto dto,
                                    Long parentId,
                                    AuditingInformation auditingInformation) {
        return new CreateCommentVoteEvent(dto, parentId, auditingInformation);
    }
}

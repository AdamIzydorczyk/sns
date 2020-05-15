package tk.aizydorczyk.sns.operation.domain.vote.commentvote.create;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.BaseCreateVoteCommand;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.domain.vote.commentvote.CommentVote;
import tk.aizydorczyk.sns.operation.infrastructure.rest.AuditingInformation;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CreateCommentVoteCommand extends BaseCreateVoteCommand<CommentVote, Comment> {
    public CreateCommentVoteCommand(BiFunction<Long, UUID, Optional<CommentVote>> findByCommentIdAndCreatedBy,
                                    Function<Long, Optional<Comment>> findParentEntityById,
                                    Function<CommentVote, CommentVote> save,
                                    Function<CommentVote, VoteDto> mapToVoteDto,
                                    Mapper mapper) {
        super(findByCommentIdAndCreatedBy, findParentEntityById, save, mapToVoteDto, mapper);
    }

    @Override
    protected CommentVote createVote(VoteDto dto, Mapper mapper) {
        return new CommentVote(dto, mapper);
    }

    @Override
    public CreateCommentVoteEvent prepareEvent(VoteDto dto,
                                               Long parentId,
                                               AuditingInformation auditingInformation) {
        return new CreateCommentVoteEvent(getClass(), dto, parentId, auditingInformation);
    }
}

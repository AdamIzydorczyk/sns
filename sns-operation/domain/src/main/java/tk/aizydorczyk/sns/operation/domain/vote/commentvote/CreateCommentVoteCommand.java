package tk.aizydorczyk.sns.operation.domain.vote.commentvote;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.Comment;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseCreateDependentCommand;

import java.util.Optional;
import java.util.function.Function;

public class CreateCommentVoteCommand extends BaseCreateDependentCommand<VoteDto, CommentVote, Comment> {
    protected CreateCommentVoteCommand(Function<Long, Optional<Comment>> findParentEntityById,
                                       Function<CommentVote, CommentVote> save,
                                       Function<CommentVote, VoteDto> mapToDto,
                                       Mapper mapper) {
        super(findParentEntityById, save, mapToDto, mapper);
    }

    @Override
    protected CommentVote createDependentEntity(VoteDto dto, Mapper mapper) {
        return new CommentVote(dto, mapper);
    }
}

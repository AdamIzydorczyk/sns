package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.domain.vote.VoteDto;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseUpdateDependentCommand;

import java.util.Optional;
import java.util.function.Function;

public class UpdatePostVoteCommand extends BaseUpdateDependentCommand<VoteDto, PostVote, Post> {
    protected UpdatePostVoteCommand(Function<Long, Optional<PostVote>> findDependentEntityById,
                                    Function<Long, Optional<Post>> findParentById,
                                    Function<PostVote, PostVote> save,
                                    Function<PostVote, VoteDto> mapToDto,
                                    Mapper mapper) {
        super(findDependentEntityById, findParentById, save, mapToDto, mapper);
    }
}

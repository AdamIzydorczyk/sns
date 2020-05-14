package tk.aizydorczyk.sns.operation.domain.vote.postvote;

import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class DeletePostVoteCommand extends BaseDeleteDependentCommand<Post> {
    protected DeletePostVoteCommand(Consumer<Long> deleteById,
                                    Function<Long, Optional<Post>> findParentById) {
        super(deleteById, findParentById);
    }
}

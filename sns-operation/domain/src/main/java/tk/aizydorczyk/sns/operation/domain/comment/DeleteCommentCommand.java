package tk.aizydorczyk.sns.operation.domain.comment;

import tk.aizydorczyk.sns.operation.domain.post.Post;
import tk.aizydorczyk.sns.operation.infrastructure.command.BaseDeleteDependentCommand;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

class DeleteCommentCommand extends BaseDeleteDependentCommand<Post> {

    DeleteCommentCommand(Consumer<Long> deleteById,
                                Function<Long, Optional<Post>> findPostById) {
        super(deleteById, findPostById);
    }
}

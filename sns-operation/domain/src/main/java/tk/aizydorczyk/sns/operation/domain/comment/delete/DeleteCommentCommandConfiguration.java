package tk.aizydorczyk.sns.operation.domain.comment.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.operation.domain.comment.CommentRepository;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class DeleteCommentCommandConfiguration {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public DeleteCommentCommandConfiguration(CommentRepository commentRepository,
                                             PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public DeleteCommentCommand deleteCommentCommand() {
        return new DeleteCommentCommand(commentRepository::deleteById,
                postRepository::findById);
    }
}

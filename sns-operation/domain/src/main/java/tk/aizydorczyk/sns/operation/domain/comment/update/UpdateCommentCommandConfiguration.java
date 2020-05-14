package tk.aizydorczyk.sns.operation.domain.comment.update;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.comment.CommentDto;
import tk.aizydorczyk.sns.operation.domain.comment.CommentRepository;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class UpdateCommentCommandConfiguration {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final Mapper mapper;

    public UpdateCommentCommandConfiguration(CommentRepository commentRepository,
                                             PostRepository postRepository,
                                             Mapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Bean
    public UpdateCommentCommand updateCommentCommand() {
        return new UpdateCommentCommand(commentRepository::findById,
                postRepository::findById,
                commentRepository::saveAndFlush,
                comment -> mapper.map(comment, CommentDto.class),
                mapper);
    }
}

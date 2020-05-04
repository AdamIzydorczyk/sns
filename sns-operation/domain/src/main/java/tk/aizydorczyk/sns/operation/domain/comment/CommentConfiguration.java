package tk.aizydorczyk.sns.operation.domain.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.operation.domain.post.PostRepository;

@Configuration
class CommentConfiguration {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final Mapper mapper;

    public CommentConfiguration(CommentRepository commentRepository,
                                PostRepository postRepository, Mapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Bean
    public CreateCommentCommand createCommentCommand() {
        return new CreateCommentCommand(postRepository::findById,
                commentRepository::save,
                comment -> mapper.map(comment, CommentDto.class),
                mapper);
    }

    @Bean
    public UpdateCommentCommand updateCommentCommand() {
        return new UpdateCommentCommand(commentRepository::findById,
                postRepository::findById,
                comment -> mapper.map(comment, CommentDto.class),
                mapper);
    }

    @Bean
    public DeleteCommentCommand deleteCommentCommand() {
        return new DeleteCommentCommand(commentRepository::deleteById,
                postRepository::findById);
    }

    @Bean
    public CommentDetailsDtoToCommentDetailsConverter commentDetailsDtoToCommentDetailsConverter() {
        return new CommentDetailsDtoToCommentDetailsConverter();
    }

}

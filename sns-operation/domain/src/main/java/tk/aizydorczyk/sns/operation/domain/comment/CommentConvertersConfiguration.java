package tk.aizydorczyk.sns.operation.domain.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConvertersConfiguration {
    @Bean
    public CommentDetailsDtoToCommentDetailsConverter commentDetailsDtoToCommentDetailsConverter() {
        return new CommentDetailsDtoToCommentDetailsConverter();
    }
}

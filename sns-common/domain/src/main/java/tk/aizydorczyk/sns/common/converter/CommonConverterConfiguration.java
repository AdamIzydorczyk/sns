package tk.aizydorczyk.sns.common.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConverterConfiguration {

    @Bean
    public CommentDetailsDtoToCommentDetailsConverter commentDetailsDtoToCommentDetailsConverter() {
        return new CommentDetailsDtoToCommentDetailsConverter();
    }

    @Bean
    public LocalDateTimeToLongConverter localDateTimeToLongConverter() {
        return new LocalDateTimeToLongConverter();
    }

}

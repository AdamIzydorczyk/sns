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

    @Bean
    public StringToLongConverter stringToLongConverter() {
        return new StringToLongConverter();
    }

    @Bean
    public StringToUuidConverter stringToUuidConverter() {
        return new StringToUuidConverter();
    }

    @Bean
    public LongToLocalDateTimeConverter longToLocalDateTimeConverter() {
        return new LongToLocalDateTimeConverter();
    }

    @Bean
    public StringToLocalDateTimeConverter stringToLocalDateTimeConverter() {
        return new StringToLocalDateTimeConverter(stringToLongConverter()::convert);
    }

}

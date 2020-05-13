package tk.aizydorczyk.sns.operation.infrastructure.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Configuration
public class RestConfiguration implements WebMvcConfigurer {

    private final Clock clock;

    public RestConfiguration(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToAuditingObjectConverter());
    }

    class StringToAuditingObjectConverter implements Converter<String, AuditingInformation> {
        @Override
        public AuditingInformation convert(String userUuidText) {
            if (isNull(userUuidText) || isEmpty(userUuidText)) {
                throw new IllegalStateException("userUuid always mast be provided");
            }
            return new AuditingInformation(UUID.fromString(userUuidText), LocalDateTime.now(clock));
        }
    }
}

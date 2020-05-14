package tk.aizydorczyk.sns.operation.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestTimeConfiguration {
    public final static Instant TEST_TIME = Instant.parse("2012-12-12T12:12:12.00Z");

    @Bean
    public Clock clock() {
        return Clock.fixed(TEST_TIME, ZoneId.of("UTC"));
    }
}

package tk.aizydorczyk.sns.operation.domain.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("tk.aizydorczyk.sns.operation.domain")
@EntityScan("tk.aizydorczyk.sns.operation.domain")
@EnableAutoConfiguration
public class H2JpaConfiguation {

}


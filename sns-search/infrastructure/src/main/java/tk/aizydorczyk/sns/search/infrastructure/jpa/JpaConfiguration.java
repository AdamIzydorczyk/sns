package tk.aizydorczyk.sns.search.infrastructure.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("tk.aizydorczyk.sns.search.domain")
@EntityScan("tk.aizydorczyk.sns.common.domain")
class JpaConfiguration {

}

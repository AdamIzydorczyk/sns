package tk.aizydorczyk.sns.operation.infrastructure.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories({"tk.aizydorczyk.sns.common.domain", "tk.aizydorczyk.sns.operation.domain"})
@EntityScan({"tk.aizydorczyk.sns.common.domain", "tk.aizydorczyk.sns.operation.domain"})
class JpaConfiguration {

}

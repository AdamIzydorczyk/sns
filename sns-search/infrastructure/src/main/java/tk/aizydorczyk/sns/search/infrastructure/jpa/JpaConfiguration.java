package tk.aizydorczyk.sns.search.infrastructure.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("tk.aizydorczyk.sns.common.domain, tk.aizydorczyk.sns.search.domain")
@EntityScan({"tk.aizydorczyk.sns.common.domain", "tk.aizydorczyk.sns.search.domain"})
class JpaConfiguration {

    @Value("${base-package-to-entity-type-scan}")
    private String basePackageToEntityScan;

    @Bean
    public EntityTypesProvider entityTypesProvider() {
        return new EntityTypesProvider(basePackageToEntityScan);
    }
}

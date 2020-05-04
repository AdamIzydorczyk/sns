package tk.aizydorczyk.sns.common.infrastructure.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfiguration {
    @Bean
    public TransactionUtils transactionUtils() {
        return new TransactionUtils();
    }
}

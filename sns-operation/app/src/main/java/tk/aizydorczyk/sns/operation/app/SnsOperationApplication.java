package tk.aizydorczyk.sns.operation.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"tk.aizydorczyk.sns.operation", "tk.aizydorczyk.sns.common"})
public class SnsOperationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnsOperationApplication.class, args);
    }
}

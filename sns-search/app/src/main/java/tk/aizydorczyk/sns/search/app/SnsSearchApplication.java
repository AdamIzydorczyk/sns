package tk.aizydorczyk.sns.search.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"tk.aizydorczyk.sns.search", "tk.aizydorczyk.sns.common"})
public class SnsSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnsSearchApplication.class, args);
    }
}

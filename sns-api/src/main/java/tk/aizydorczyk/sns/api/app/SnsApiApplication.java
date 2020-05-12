package tk.aizydorczyk.sns.api.app;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
@EnableAdminServer
public class SnsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnsApiApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/sns/search/**")
                                .uri("http://localhost:8082"))
                .route(r ->
                        r.path("/sns/operation/**")
                                .filters(spec -> spec.addRequestHeader("userUuid", UUID.randomUUID().toString()))
                                .uri("http://localhost:8083"))
                .build();
    }
}
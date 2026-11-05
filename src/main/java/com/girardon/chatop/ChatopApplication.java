package com.girardon.chatop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "${spring.application.name}",
                version = "${spring.application.version}",
                description = "${spring.application.description}"
        )
)
@SpringBootApplication
@EnableJpaAuditing
public class ChatopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatopApplication.class, args);
    }

}

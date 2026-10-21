package com.girardon.chatop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Chatop API",
                version = "1.0",
                description = "API documentation for Chatop"
        )
)
@SpringBootApplication
public class ChatopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatopApplication.class, args);
    }

}

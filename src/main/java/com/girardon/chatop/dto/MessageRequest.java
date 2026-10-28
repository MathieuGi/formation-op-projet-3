package com.girardon.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MessageRequest {
    @NotBlank(message = "Required message")
    String message;

    @NotNull
    int userId;

    @NotNull
    int rentalId;
}

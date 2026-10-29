package com.girardon.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {
    @NotBlank(message = "Required message")
    String message;

    @NotNull
    int user_id;

    @NotNull
    int rental_id;
}

package com.girardon.chatop.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Required username")
        String name,

        @NotBlank(message = "Required email")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Required password")
        @Size(min = 8, message = "Password should be at least 8 characters long")
        String password
) {
}

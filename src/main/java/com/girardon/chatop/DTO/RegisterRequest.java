package com.girardon.chatop.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotNull(message = "Required username")
        String name,

        @NotNull(message = "Required email")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Required password")
        @Size(min = 8, message = "Password should be at least 8 characters long")
        String password
) {
}

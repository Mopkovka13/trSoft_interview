package com.example.auth.web.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @NotBlank(message = "Username can't be empty")
    private String username;

    @Size(min = 5, max = 255, message = "Password must be between 5 and 255 characters")
    @NotBlank(message = "Password can't be empty")
    private String password;
}

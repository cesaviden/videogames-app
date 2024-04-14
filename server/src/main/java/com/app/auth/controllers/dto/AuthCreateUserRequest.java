package com.app.auth.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
        @NotBlank String email,
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String password) {

}

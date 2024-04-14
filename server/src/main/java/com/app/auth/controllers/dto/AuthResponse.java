package com.app.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "token", "status"})
public record AuthResponse(String message,
        String token,
        boolean status) {
}
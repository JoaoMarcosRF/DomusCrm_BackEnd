package com.domus.api.dto;

public record LoginRequest(
        String email,
        String password
)
{}

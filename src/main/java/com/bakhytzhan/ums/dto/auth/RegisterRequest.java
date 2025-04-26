package com.bakhytzhan.ums.dto.auth;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String role
) {}

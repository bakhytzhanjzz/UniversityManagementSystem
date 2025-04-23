package com.bakhytzhan.ums.controller;

import com.bakhytzhan.ums.dto.auth.AuthResponse;
import com.bakhytzhan.ums.dto.auth.LoginRequest;
import com.bakhytzhan.ums.dto.auth.RegisterRequest;
import com.bakhytzhan.ums.model.User;
import com.bakhytzhan.ums.security.JwtUtil;
import com.bakhytzhan.ums.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}


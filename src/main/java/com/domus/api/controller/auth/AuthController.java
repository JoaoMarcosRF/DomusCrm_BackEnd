package com.domus.api.controller.auth;

import com.domus.api.dto.LoginRequest;
import com.domus.api.dto.response.ApiResponse;
import com.domus.api.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.ok("Login successful.", token));
    }
}

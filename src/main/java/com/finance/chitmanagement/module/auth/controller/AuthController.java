package com.finance.chitmanagement.module.auth.controller;

import com.finance.chitmanagement.module.auth.dto.SignupRequest;
import com.finance.chitmanagement.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.registerCustomer(request);
        return ResponseEntity.ok("User registered successfully");
    }
}

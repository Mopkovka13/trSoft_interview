package com.example.auth.web.controller;

import com.example.auth.service.AuthService;
import com.example.auth.web.dto.auth.JwtAuthResponse;
import com.example.auth.web.dto.auth.SignInRequest;
import com.example.auth.web.dto.auth.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public JwtAuthResponse signUp(@Valid @RequestBody SignUpRequest request){
        return authService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthResponse signIn(@Valid @RequestBody SignInRequest request){
        return authService.signIn(request);
    }
}

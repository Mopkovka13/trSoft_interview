package com.example.auth.service;

import com.example.auth.domain.user.*;
import com.example.auth.web.dto.auth.JwtAuthResponse;
import com.example.auth.web.dto.auth.SignInRequest;
import com.example.auth.web.dto.auth.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(SignUpRequest request) {
        var user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .personalInfo(new PersonalInfo())
                .contactInfo(new ContactInfo())
                .userPhoto(new UserPhoto())
                .build();

        userService.createUser(user);
        var jwt = jwtService.generateToken(user);

        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        var authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword())
        );

        // Сохраняем в контекст пользователя
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = (UserEntity) userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        return new JwtAuthResponse(jwt);
    }
}

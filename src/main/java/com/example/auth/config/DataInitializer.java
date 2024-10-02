package com.example.auth.config;

import com.example.auth.domain.user.*;
import com.example.auth.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        createDefaultUser();
    }

    private void createDefaultUser() {
        var user = UserEntity.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_USER)
                .personalInfo(new PersonalInfo())
                .contactInfo(new ContactInfo())
                .userPhoto(new UserPhoto())
                .build();

        userService.createUser(user);
    }
}
package com.example.auth.web.controller;

import com.example.auth.domain.user.UserEntity;
import com.example.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<UserEntity> getCurrentUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }
}

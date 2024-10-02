package com.example.auth.web.controller;

import com.example.auth.service.UserPhotoService;
import com.example.auth.web.dto.user.userPhoto.UserPhotoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/user-photo")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    @PostMapping("/update")
    public ResponseEntity<UserPhotoResponse> updateUserPhoto(
            @RequestParam("file") MultipartFile file)
            throws RuntimeException {
        UserPhotoResponse response =
                userPhotoService.updateUserPhoto(file);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserPhotoResponse> getUserPhoto() {
        UserPhotoResponse response =
                userPhotoService.getUserPhoto();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUsetPhoto() {
        userPhotoService.deleteUserPhoto();

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

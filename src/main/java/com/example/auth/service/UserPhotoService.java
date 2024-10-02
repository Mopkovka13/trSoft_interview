package com.example.auth.service;

import com.example.auth.domain.user.UserEntity;
import com.example.auth.domain.user.UserPhoto;
import com.example.auth.mapper.UserPhotoMapper;
import com.example.auth.web.dto.user.userPhoto.UserPhotoResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UserPhotoService {
    private final UserService userService;
    private final UserPhotoMapper mapper;

    @Transactional
    public UserPhotoResponse updateUserPhoto(MultipartFile file) {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        // Проверка типа файла
        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file type. Only images are allowed.");
        }

        UserPhoto userPhoto;
        try {
            // Преобразование запроса в сущность
            userPhoto = UserPhoto
                    .builder()
                    .id(user.getUserPhoto().getId())
                    .filename(file.getOriginalFilename())
                    .photo(file.getBytes()) // Может выбросить IOException
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading the photo file", e);
        }

        // Сохранение данных
        user.setUserPhoto(userPhoto);
        userService.save(user);

        // Возврат ответа
        return mapper.entityToResponse(user.getUserPhoto());
    }

    public UserPhotoResponse getUserPhoto() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        var user2 = user.getUserPhoto();
        return mapper.entityToResponse(user2);
    }

    public void deleteUserPhoto() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        var userPhoto = UserPhoto
                .builder()
                .id(user.getUserPhoto().getId())
                .build();

        user.setUserPhoto(userPhoto);
        userService.save(user);
    }
}

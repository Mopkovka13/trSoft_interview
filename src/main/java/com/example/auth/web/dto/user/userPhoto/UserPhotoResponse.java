package com.example.auth.web.dto.user.userPhoto;

import lombok.Data;

@Data
public class UserPhotoResponse {
    private Long id;
    private byte[] photo;
    private String filename;
}

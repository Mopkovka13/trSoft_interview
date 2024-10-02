package com.example.auth.mapper;

import com.example.auth.domain.user.UserPhoto;
import com.example.auth.web.dto.user.userPhoto.UserPhotoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPhotoMapper {
    // Преобразование из сущности в Response
    UserPhotoResponse entityToResponse(UserPhoto entity);
}

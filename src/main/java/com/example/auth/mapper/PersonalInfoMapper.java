package com.example.auth.mapper;

import com.example.auth.domain.user.PersonalInfo;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoRequest;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonalInfoMapper {
    // Преобразование из Request в сущность
    PersonalInfo requestToEntity(PersonalInfoRequest request);

    // Преобразование из сущности в Response
    PersonalInfoResponse entityToResponse(PersonalInfo entity);
}

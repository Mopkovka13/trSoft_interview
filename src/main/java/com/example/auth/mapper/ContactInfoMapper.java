package com.example.auth.mapper;

import com.example.auth.domain.user.ContactInfo;
import com.example.auth.web.dto.user.contactInfo.ContactInfoRequest;
import com.example.auth.web.dto.user.contactInfo.ContactInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {
    // Преобразование из Request в сущность
    ContactInfo requestToEntity(ContactInfoRequest request);

    // Преобразование из сущности в Response
    ContactInfoResponse entityToResponse(ContactInfo entity);
}

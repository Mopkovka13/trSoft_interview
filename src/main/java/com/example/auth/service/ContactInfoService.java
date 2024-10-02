package com.example.auth.service;

import com.example.auth.domain.user.ContactInfo;
import com.example.auth.domain.user.UserEntity;
import com.example.auth.mapper.ContactInfoMapper;
import com.example.auth.web.dto.user.contactInfo.ContactInfoRequest;
import com.example.auth.web.dto.user.contactInfo.ContactInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContactInfoService {
    private final UserService userService;
    private final ContactInfoMapper mapper;

    @Transactional
    public ContactInfoResponse updateContactInfo(ContactInfoRequest request) {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        // Преобразование запроса в сущность
        ContactInfo contactInfo = mapper.requestToEntity(request);
        contactInfo.setId(user.getContactInfo().getId());

        // Сохранение данных
        user.setContactInfo(contactInfo);
        userService.save(user);

        // Возврат ответа
        return mapper.entityToResponse(user.getContactInfo());
    }

    public ContactInfoResponse getContactInfo() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        // Возврат ответа
        return mapper.entityToResponse(user.getContactInfo());
    }

    public void deleteContactInfo() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        var contactInfo = ContactInfo
                .builder()
                .id(user.getContactInfo().getId())
                .build();

        user.setContactInfo(contactInfo);
        userService.save(user);
    }
}

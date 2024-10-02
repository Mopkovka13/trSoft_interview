package com.example.auth.service;

import com.example.auth.domain.user.PersonalInfo;
import com.example.auth.domain.user.UserEntity;
import com.example.auth.mapper.PersonalInfoMapper;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoRequest;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PersonalInfoService {
    private final UserService userService;
    private final PersonalInfoMapper mapper;

    @Transactional
    public PersonalInfoResponse updatePersonalInfo(PersonalInfoRequest request) {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        // Преобразование запроса в сущность
        PersonalInfo personalInfo = mapper.requestToEntity(request);
        personalInfo.setId(user.getPersonalInfo().getId());

        // Сохранение данных
        user.setPersonalInfo(personalInfo);
        userService.save(user);

        // Возврат ответа
        return mapper.entityToResponse(user.getPersonalInfo());
    }

    public PersonalInfoResponse getPersonalInfo() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        // Возврат ответа
        return mapper.entityToResponse(user.getPersonalInfo());
    }

    public void deletePersonalInfo() {
        // Проверка на наличие текущего пользователя
        UserEntity user = userService.getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("Current user not found");
        }

        var personalInfo = PersonalInfo
                .builder()
                .id(user.getPersonalInfo().getId())
                .build();

        user.setPersonalInfo(personalInfo);
        userService.save(user);
    }
}

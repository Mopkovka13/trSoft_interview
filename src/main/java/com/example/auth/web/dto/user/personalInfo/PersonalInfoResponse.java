package com.example.auth.web.dto.user.personalInfo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
}

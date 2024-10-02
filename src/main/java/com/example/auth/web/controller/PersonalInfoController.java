package com.example.auth.web.controller;

import com.example.auth.service.PersonalInfoService;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoRequest;
import com.example.auth.web.dto.user.personalInfo.PersonalInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/personal-info")
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @PostMapping("/update")
    public ResponseEntity<PersonalInfoResponse> update(
            @Valid @RequestBody PersonalInfoRequest request) {
        PersonalInfoResponse response =
                personalInfoService.updatePersonalInfo(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<PersonalInfoResponse> get() {
        PersonalInfoResponse response =
                personalInfoService.getPersonalInfo();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        personalInfoService.deletePersonalInfo();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

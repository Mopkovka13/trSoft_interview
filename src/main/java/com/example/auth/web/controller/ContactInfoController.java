package com.example.auth.web.controller;

import com.example.auth.service.ContactInfoService;
import com.example.auth.web.dto.user.contactInfo.ContactInfoRequest;
import com.example.auth.web.dto.user.contactInfo.ContactInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/contact-info")
public class ContactInfoController {
    private final ContactInfoService contactInfoService;

    @PostMapping("/update")
    public ResponseEntity<ContactInfoResponse> update(
            @Valid @RequestBody ContactInfoRequest request) {
        ContactInfoResponse response =
                contactInfoService.updateContactInfo(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ContactInfoResponse> get() {
        ContactInfoResponse response =
                contactInfoService.getContactInfo();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        contactInfoService.deleteContactInfo();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

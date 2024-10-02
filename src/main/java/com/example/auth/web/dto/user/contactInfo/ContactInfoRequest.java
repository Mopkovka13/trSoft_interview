package com.example.auth.web.dto.user.contactInfo;

import com.example.auth.utils.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactInfoRequest {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone must not be blank")
    @Phone(message = "Phone number must be 11 digits")
    private String phone;
}

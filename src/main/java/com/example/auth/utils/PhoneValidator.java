package com.example.auth.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    // TO DO: улучшить по необходимости регулярное выражение
    private static final String PHONE_NUMBER_REGEX = "^\\d{11}$";

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }
}

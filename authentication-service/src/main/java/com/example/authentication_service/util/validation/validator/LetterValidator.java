package com.example.authentication_service.util.validation.validator;

import com.example.authentication_service.util.validation.annotation.Letter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LetterValidator implements ConstraintValidator<Letter, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        for(char c : string.toCharArray()) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}

package com.example.authentication_service.util.validation.annotation;

import com.example.authentication_service.util.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "invalid password format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

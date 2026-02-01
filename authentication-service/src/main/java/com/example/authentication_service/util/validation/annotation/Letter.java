package com.example.authentication_service.util.validation.annotation;

import com.example.authentication_service.util.validation.validator.LetterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LetterValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Letter {
    String message() default "string contains not only letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

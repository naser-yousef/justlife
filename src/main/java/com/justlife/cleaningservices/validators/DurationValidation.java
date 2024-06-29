package com.justlife.cleaningservices.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DurationValidator.class)
public @interface DurationValidation {

    String message() default "Invalid Duration";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

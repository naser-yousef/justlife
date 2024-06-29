package com.justlife.cleaningservices.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class DurationValidator implements ConstraintValidator<DurationValidation, Integer> {

    @Value("${allowed.periods}")
    private List<Integer> allowedPeriods;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return allowedPeriods.contains(integer);
    }
}

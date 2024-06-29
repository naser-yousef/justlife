package com.justlife.cleaningservices.service;

import com.justlife.cleaningservices.entity.HasId;
import com.justlife.cleaningservices.exceptions.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractService {

    public void throwValidationError(String message) {
        throw new ValidationException(message);
    }

    public <T> List<T> extractIds(List<? extends HasId<T>> objects) {
        return objects.stream()
            .map(HasId::getId)
            .collect(Collectors.toList());
    }

}

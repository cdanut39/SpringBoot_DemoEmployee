package com.learning.spring.rest.employees.utils;

import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class BindingResultErrors {

    public static List<ValidationError> getErrors(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(e -> new ValidationError(e.getField(), String.valueOf(e.getRejectedValue()), e.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}


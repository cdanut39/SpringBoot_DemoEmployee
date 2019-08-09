package com.learning.spring.rest.employees.exceptions.custom.project;

import com.learning.spring.rest.employees.exceptions.handler.ValidationError;

import java.util.List;

public class ProjectNotValidException extends Exception {
    private List<ValidationError> fieldErrors;

    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ValidationError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ProjectNotValidException(String message, List<ValidationError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}

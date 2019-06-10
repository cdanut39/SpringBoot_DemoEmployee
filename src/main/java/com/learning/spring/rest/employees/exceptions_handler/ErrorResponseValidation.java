package com.learning.spring.rest.employees.exceptions_handler;

import java.util.List;

public class ErrorResponseValidation extends ErrorResponse {

    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}

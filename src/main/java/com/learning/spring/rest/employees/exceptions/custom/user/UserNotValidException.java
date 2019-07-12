package com.learning.spring.rest.employees.exceptions.custom.user;

import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserNotValidException extends Exception {

    private static final Logger logger = LogManager.getLogger(UserNotValidException.class);

    private List<ValidationError> fieldErrors;

    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ValidationError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public UserNotValidException(String message, List<ValidationError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }


}

package com.learning.spring.rest.employees.exceptions.community;

import com.learning.spring.rest.employees.exceptions_handler.ValidationError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommunityNotValidException extends Exception {
    private static final Logger logger = LogManager.getLogger(CommunityNotValidException.class);

    private List<ValidationError> fieldErrors;

    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ValidationError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public CommunityNotValidException(String message, List<ValidationError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}

package com.learning.spring.rest.employees.exceptions.department;

import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DepartmentNotValidException extends Exception {
    private static final Logger logger = LogManager.getLogger(DepartmentNotValidException.class);

    private List<ValidationError> fieldErrors;

    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ValidationError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public DepartmentNotValidException(String message, List<ValidationError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}

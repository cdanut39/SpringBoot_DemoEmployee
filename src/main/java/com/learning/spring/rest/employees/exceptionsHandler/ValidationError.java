package com.learning.spring.rest.employees.exceptionsHandler;

public class ValidationError {

    private String field;
    private String rejectedValue;
    private String defaultMessage;

    public ValidationError(String field, String rejectedValue, String defaultMessage) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}

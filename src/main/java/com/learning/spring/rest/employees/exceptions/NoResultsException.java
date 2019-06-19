package com.learning.spring.rest.employees.exceptions;

public class NoResultsException extends Exception {

    private String message;

    public NoResultsException(String message) {
        super(message);
    }
}

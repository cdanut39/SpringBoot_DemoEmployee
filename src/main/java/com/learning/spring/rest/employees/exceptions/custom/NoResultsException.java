package com.learning.spring.rest.employees.exceptions.custom;

public class NoResultsException extends Exception {

    public NoResultsException(String message) {
        super(message);
    }
}

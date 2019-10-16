package com.learning.spring.rest.employees.exceptions.custom.user;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}

package com.learning.spring.rest.employees.exceptions.community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommunityAlreadyExistsException extends Exception {
    private static final Logger logger = LogManager.getLogger(CommunityAlreadyExistsException.class);

    public CommunityAlreadyExistsException(String message) {
        super(message);

    }

}

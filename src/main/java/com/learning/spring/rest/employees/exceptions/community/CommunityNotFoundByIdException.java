package com.learning.spring.rest.employees.exceptions.community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommunityNotFoundByIdException extends Exception {

    private static final Logger logger = LogManager.getLogger(CommunityNotFoundByIdException.class);


    public CommunityNotFoundByIdException(String message, int id) {
        super(message);
        logger.error("No community found with id=" + id);
    }
}

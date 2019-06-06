package com.learning.spring.rest.employees.exceptions.Community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommunityNotFoundByIdException extends Exception {

    private static final Logger logger = LogManager.getLogger(CommunityNotFoundByIdException.class);


    public CommunityNotFoundByIdException(String message, int id) {
        super(message);
        logger.error("No Community found with id=" + id);
    }
}

package com.learning.spring.rest.employees.exceptions.Community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommunityNotFoundByNameException extends Exception {

    private static final Logger logger = LogManager.getLogger(CommunityNotFoundByNameException.class);


    public CommunityNotFoundByNameException(String message, String CommunityName) {
        super(message);
        logger.error("No Community found with name=" + CommunityName);
    }
}

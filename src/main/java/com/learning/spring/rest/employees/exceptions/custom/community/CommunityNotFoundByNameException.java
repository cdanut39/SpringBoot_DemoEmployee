package com.learning.spring.rest.employees.exceptions.custom.community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommunityNotFoundByNameException extends Exception {

    private static final Logger logger = LogManager.getLogger(CommunityNotFoundByNameException.class);


    public CommunityNotFoundByNameException(String message, String communityName) {
        super(message);
        logger.error("No community found with name={}",communityName);
    }
}

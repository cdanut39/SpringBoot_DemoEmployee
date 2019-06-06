package com.learning.spring.rest.employees.exceptions.Community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommunityCanNotBeRemovedException extends Exception {

    private static final Logger logger = LogManager.getLogger(DefaultCommunityCanNotBeRemovedException.class);

    public DefaultCommunityCanNotBeRemovedException(String message) {
        super(message);
        logger.error("Community can not be removed");
    }
    }



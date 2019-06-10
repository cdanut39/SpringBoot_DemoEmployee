package com.learning.spring.rest.employees.exceptions.community;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommunityCanNotBeRemovedException extends Exception {

    private static final Logger logger = LogManager.getLogger(DefaultCommunityCanNotBeRemovedException.class);

    public DefaultCommunityCanNotBeRemovedException(String message) {
        super(message);
        logger.error("community can not be removed");
    }
    }



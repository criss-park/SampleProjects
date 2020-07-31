package com.simplify.logback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogbackService {

    Logger logger = LoggerFactory.getLogger(getClass());

    public void log() {
        logger.info("hello");
    }
}

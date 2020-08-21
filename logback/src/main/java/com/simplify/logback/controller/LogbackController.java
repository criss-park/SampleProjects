package com.simplify.logback.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class LogbackController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final static List VALID_LEVELS = Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR");

    @RequestMapping( value="/" )
    public @ResponseBody String test(){
        logger.info("info log");

        for(Map.Entry<String, String> entry : System.getenv().entrySet()){
            logger.info("[ENV] " + entry.getKey() + " : " + entry.getValue());
        }

        try {
            logger.info(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "TEST";
    }

    @RequestMapping( value="/changeLogLevel/{level}" )
    public @ResponseBody String changeLogLevel(@PathVariable String level){

        String returnMessage = "";

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.simplify.logback");

        if(VALID_LEVELS.contains(level.toUpperCase())){
            Level oldLevel = logger.getLevel();
            logger.setLevel(Level.toLevel(level));

            returnMessage = "LOG LEVEL CHANGE FROM " + oldLevel + " TO " + logger.getLevel();

        }else{

            returnMessage = "NOT SUPPORT LOG LEVEL : " + level;

        }

        testPrintLog("TEST");

        return returnMessage;
    }

    private void testPrintLog(String logMessage) {

        if (logger.isTraceEnabled()) {
            logger.trace("Start step {}", logMessage);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Start step {}", logMessage);
        }

        if (logger.isInfoEnabled()) {
            logger.info("Start step {}", logMessage);
        }

        if (logger.isWarnEnabled()) {
            logger.warn("Start step {}", logMessage);
        }

        if (logger.isErrorEnabled()) {
            logger.error("Start step {}", logMessage);
        }
    }
}

package com.gittigidiyorselenium.util;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class UtilLogger {

    private static  Logger logger = LogManager.getLogger(UtilLogger.class);


    public static void infoLog(String message){
        logger.info(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }
    public static void error(String message){
        logger.error(message);
    }
    public static void fatal(String message){
        logger.fatal(message);
    }







}

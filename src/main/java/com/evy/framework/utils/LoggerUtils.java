package com.evy.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*** Utility class for logging messages using Log4j2.**/


public final class LoggerUtils {

    private LoggerUtils(){}


    private static Logger getLogger(Class<?>clazz){
        return LogManager.getLogger(clazz);
    }


    public static void info(Class<?>clazz,String message){
        getLogger(clazz).info(message);
    }

    public static void warn(Class<?>clazz,String message){
        getLogger(clazz).warn(message);
    }

    public static void error(Class<?>clazz,String message,Exception e){
        getLogger(clazz).error(message,e);
    }
}

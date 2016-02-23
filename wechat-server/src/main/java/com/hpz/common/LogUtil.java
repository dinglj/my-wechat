package com.hpz.common;

import org.apache.log4j.Logger;

/**
 * Created by tommy on 15/10/30.
 */
public class LogUtil {

//    private  static Logger serviceLogger = Logger.getLogger("logService");
    private static Logger logger = Logger.getLogger("root");
//    private static Logger failedLogger = Logger.getLogger("logFailed");

    private static Logger taskLogger = Logger.getLogger("tasklog");

//    public static Logger getServiceLogger(){
//        return serviceLogger;
//    };

    public static Logger getLogger(){
        return logger;
    }

//    public static Logger getFailedLogger(){return failedLogger;}

    public static Logger getTaskLogger(){
        return taskLogger;
    }
}

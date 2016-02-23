package com.hpz.common.aspectjLog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by mao on 2015/6/25.
 */
public class LogServiceUtil {
    //这个日志用来单独记录 每个公共接口被调用所执行的时间 的日志信息
    private static Log serviceLogger  = LogFactory.getLog("LogService");

    public LogServiceUtil(){}

    public static void info(String infomation){

        serviceLogger.info(infomation);
    }

    public static void error(String infomation) {

        serviceLogger.error(infomation);
    }
}

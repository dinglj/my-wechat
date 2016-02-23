package com.hpz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * nothing at all
 * Created by tommy on 15/5/5.
 */
public class CommonUtil {


        private static Logger info = LoggerFactory.getLogger(CommonUtil.class);



        public CommonUtil(){}


        public static void info(String infomation){

                info.info(infomation);
        }

        public static void info(String message, Object... params) {
                info.info(message, params);
        }

        public static void error(String infomation){

                info.error(infomation);
        }

        public static void error(String message, Object... params) {
                info.error(message,params);
        }

}

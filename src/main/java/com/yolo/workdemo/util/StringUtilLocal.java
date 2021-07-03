package com.yolo.workdemo.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtilLocal extends StringUtils {

    public static boolean isNullStr(String... args) {
        boolean falg = false;
        for (String arg : args) {
            if (StringUtils.isBlank(arg)) {
                falg = true;
                return falg;
            }
        }
        return falg;
    }

}

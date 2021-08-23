package com.yolo.workdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 白名单列表
 */
@Slf4j
public class WhiteUtil {
    static String s = "12313123,21";

    public static Set<String> getWhiteList() {
        try {
            if (StringUtils.isBlank(s)) {
                return null;
            }
            return Arrays.stream(s.split(",")).collect(Collectors.toSet());
        } catch (Exception e) {
            log.error("获取白名单异常", e);
            return null;
        }
    }
}

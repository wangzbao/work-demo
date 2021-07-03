package com.yolo.workdemo.controller;

import com.yolo.workdemo.util.StringUtilLocal;
import org.apache.commons.lang3.StringUtils;

public class TestController {

    public String sayHello(String name) {
        return "hello" + name;
    }

    public static void main(String[] args) {
        String s1 = "11";
        String s2 = "22";
        String s3 = "";

        boolean nullStr = StringUtilLocal.isNullStr(s1, s2);
        System.out.println(nullStr);
        System.out.println(StringUtils.isBlank(s3));
    }
}

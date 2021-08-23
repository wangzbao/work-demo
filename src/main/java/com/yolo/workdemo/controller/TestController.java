package com.yolo.workdemo.controller;

import com.yolo.workdemo.domain.User;
import com.yolo.workdemo.util.StringUtilLocal;
import com.yolo.workdemo.util.WhiteUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TestController {
    private static String s4 = "";

    public String sayHello(String name) {
        return "hello" + name;
    }

    public static void main(String[] args) {
        String i = "1,2, 3,";
        String s = "张三, 李四";
        String[] ir = i.split(",");
        String[] name = s.split(",");
        System.out.println(name.length);
//        for (int j = 0; j < ir.length; j++) {
//            System.out.println(ir[j]);
//            System.out.println(name.length >= j + 1 ? name[j] : null);
//        }

        String one = "12313123";
        String[] split = one.split(",");
        for (int i1 = 0; i1 < split.length; i1++) {
            System.out.println(split[i1]);
        }
        User user = new User();
        user.setAge(1);
        user.setSex("nan");
        user.setName(1 < 2 ? "zhangsan" : null);

//        System.out.println(user.getName() != null ? user.getName() : user.getAge());
        Set<String> whiteList = WhiteUtil.getWhiteList();
        System.out.println("!!!!  "+whiteList);
        String sex = user.getSex();
        if (CollectionUtils.isNotEmpty(whiteList) && whiteList.contains("12313123")) {
            sex = user.getSex()+"~~~";
        } else {
            sex = sex;
        }
        System.out.println(sex);
    }
}

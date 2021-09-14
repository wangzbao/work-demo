package com.yolo.workdemo.controller;

import com.yolo.workdemo.domain.User;

import java.util.ArrayList;
import java.util.List;

public class GitTest {

    public static void main(String[] args) {
        System.out.println("-------");
        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 25);
        User wangwu = new User("王五", "女", 21);
        List<User> list = new ArrayList<>();
        list.add(zhangsan);
        list.add(lisi);
        list.add(wangwu);

        list.forEach(user -> {
            user.setAge(user.getAge() * 2);
        });
        System.out.println(list);
    }
}

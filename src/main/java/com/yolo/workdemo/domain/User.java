package com.yolo.workdemo.domain;


import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class User {
    @Size(max = 4, message = "请输入正确名字")
    private String name;
    private String sex;
    private Integer age;
    private String head;
    private String head1;
    private String no;
    private boolean isHistory;

    public User(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User() {
    }

    public static void main(String[] args) {
        int index =1 ;
        int foo[] = new int[3];
        int bar = foo[index];
        int value = bar+index;
        System.out.println(value);
    }
}

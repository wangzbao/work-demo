package com.yolo.workdemo.domain;


import lombok.Data;

@Data
public class User {
    private String name;
    private String sex;
    private Integer age;

    public User(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User() {
    }
}

package com.yolo.workdemo.service;

public interface OrderService {
    /**
     * 需要被代理的方法
     *
     * @return
     */
    String addOrder(String userName, String userPwd);
}
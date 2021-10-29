package com.yolo.workdemo.service.impl;

import com.yolo.workdemo.service.OrderService;

public class OrderServiceProxy implements OrderService {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceProxy(new OrderServiceImpl());
        orderService.addOrder("mayikt", "123456");
    }

    private OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String addOrder(String userName, String userPwd) {
        System.out.println("使用静态代理类打印日志开始：userName:" + userName + "," + userPwd);
        String result = orderService.addOrder(userName, userPwd);
        System.out.println(result);
        System.out.println("使用静态代理类打印日志结束：userName:" + userName + "," + userPwd);
        return result;
    }
}


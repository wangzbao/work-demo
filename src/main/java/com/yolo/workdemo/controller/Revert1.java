package com.yolo.workdemo.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Revert1 {
    String one = "revert1";

    public static void main(String[] args) {
//        arrayListLearn();
//        linkedListLearn();
//        stackLearn();
//        hashMapLearn();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
    }

    private static void hashMapLearn() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
    }

    private static void stackLearn() {
        Stack<String> stack = new Stack<>();
        stack.push("wz");
        stack.push("zs");
        stack.push("ls");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        stack.push("7");
        stack.push("8");
        stack.push("9");
        stack.push("10");
        stack.push("11");
        stack.push("12");
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }

    private static void linkedListLearn() {
        LinkedList<String> list = new LinkedList<>();
        list.add("张三");
        System.out.println(list);
        list.addLast("李四");
        System.out.println(list);
        list.addFirst("赵六");
        System.out.println(list);
        list.add(1, "王五");
        System.out.println(list);

        String first = list.getFirst();
        System.out.println(first);
        String peek = list.peek();
        System.out.println(peek);
        String last = list.getLast();
        System.out.println(last);

        String s = list.get(1);
        System.out.println(s);
        System.out.println(list);

//        String s1 = list.removeFirst();
//        System.out.println(s1);
//        System.out.println(list);
//
//        String last1 = list.removeLast();
//        System.out.println(last1);
//        System.out.println(list);

        String remove = list.remove(1);
        System.out.println(remove);
        System.out.println("===" + list);

    }

    private static void arrayListLearn() {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.set(1, "王五");
        list.add(2, "赵六");
        int zero = 3;
        int a = 5;
        int i = a << 2;
        System.out.println(a);
        System.out.println(i);
        System.out.println(zero >> 1);
        System.out.println(zero + (zero >> 1));
    }

}

package com.yolo.workdemo.learn;

import java.util.ArrayList;

public class ArrayListLearn {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.set(1, "王五");
        list.add(1, "赵六");
        System.out.println(list.get(1));

        //ArrayList 性能最好的方法，也是特征所在
        list.get(1);

        // remove
        list.remove(1);
    }
}

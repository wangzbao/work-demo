package com.yolo.workdemo.service.impl;

import com.yolo.workdemo.service.Prototype;
import lombok.Data;

@Data
public class ConcretePrototype implements Prototype {

    private String field1;
    private String field2;

    @Override
    public Prototype clone() {
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setField1(field1);
        prototype.setField2(field2);
        return prototype;

        //原型模式，顾名思义，其实说白了，就是让一个对象可以自己拷贝自己，对象把自己当成一个原型，然后提供一个方法出去，
        // 外部要一个对象的克隆和拷贝，直接就copy一份就可以了，但是这里要记住深拷贝和浅拷贝的区别

        //因为一个对象可能还持有别的对象的引用，浅拷贝就是不管引用的其他对象了；深拷贝就是将引用的对象也一起拷贝一份；一般原型模式都是要支持深拷贝的

        //而且其实一般实现原型模式的时候，直接是通过覆盖Object类的clone()方法即可，在里面实现自己的拷贝逻辑就可以了
    }
}

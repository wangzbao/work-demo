package com.yolo.workdemo;

import com.yolo.workdemo.controller.TestController;
import com.yolo.workdemo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import java.beans.Transient;

import static org.junit.Assert.assertEquals;


//这个注解是执行单元测试的时候，不是直接去执行里面的单元测试的方法，二十再执行之前，做一些准备工作
//需要先初始化一个spring容器的，所以此处先用法springRunner.class准备环境
@RunWith(SpringRunner.class)
//会从最顶层的包结构开始找，找到标注了springbootApplication注解的一个类，算是启动类
//然后执行这个启动类的main方法，就可以创建spring容器，给后面的单元测试提供完整的环境
@SpringBootTest(classes = WorkDemoApplication.class)
@Rollback
class WorkDemoApplicationTests {
    private User user = new User();


    @Test
    void contextLoadsTest() {
        System.out.println(111);
    }

    @Test
    public void sayTest() {
        TestController test = new TestController();
//        int i = 1 / 0;
        System.out.println(user.getAge());
        String result = test.sayHello("world");
        assertEquals("helloworld", result);
        System.out.println("断言执行成功");
    }


}

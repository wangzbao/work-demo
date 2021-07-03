package com.yolo.workdemo;

import com.yolo.workdemo.controller.TestController;
import com.yolo.workdemo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkDemoApplication.class)
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

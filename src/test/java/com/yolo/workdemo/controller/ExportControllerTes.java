package com.yolo.workdemo.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
//通过这个注解表明你要测试的是controller
@WebMvcTest(ExportController.class)
public class ExportControllerTes {
    //模拟对controller发送http请求
    @Autowired
    private MockMvc mvc;

//    @MockBean

}
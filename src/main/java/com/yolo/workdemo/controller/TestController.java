package com.yolo.workdemo.controller;

import com.yolo.workdemo.common.AjaxResult;
import com.yolo.workdemo.domain.TaskStatsUserDetailVO;
import com.yolo.workdemo.domain.User;
import com.yolo.workdemo.domain.enums.MtTaskStatusPeriodEnum;
import com.yolo.workdemo.util.WhiteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
    private static String s4 = "";

    public String sayHello(String name) {
        return "hello" + name;
    }

    @GetMapping("/list")
    public AjaxResult testSize(@RequestParam("dtArr") List<String> dtArr,
                               @RequestParam("idArr") List<String> idArr) {
        System.out.println("进入" + dtArr.size());
        for (int i = 0; i < dtArr.size(); i++) {
            System.out.println("==============" + dtArr.get(i) + "======" + idArr.get(i));
        }
        return AjaxResult.success();
    }

    public static void main(String[] args) {
        Long pay = 369900L;
        System.out.println(NumberUtils.toScaledBigDecimal(pay / 100d, 1, RoundingMode.HALF_UP));
        String r = "10 12 9 9 ? 2021";
        String[] ssplit = r.split(" ");
        String re = (String.join(":", new CharSequence[]{ssplit[1], ssplit[0]}));
        System.out.println(re);

        List<String> dtArr = new ArrayList<>();
        List<String> taskIdArr = new ArrayList<>();
        int period = 1;
        dtArr.add("2021-10-01");
        dtArr.add("2021-10-02");
        dtArr.add("2021-10-03");

        taskIdArr.add("task1");
        taskIdArr.add("task2");
        taskIdArr.add("task3");
        List<TaskStatsUserDetailVO> list = new ArrayList<>();
        for (int i = 0; i < dtArr.size(); i++) {
            TaskStatsUserDetailVO vo = new TaskStatsUserDetailVO();
            vo.setDt(dtArr.get(i));
            vo.setTaskId(taskIdArr.get(i));
            vo.setPeriod(period);
            list.add(vo);
        }
        System.out.println(list);
        String i = "1,2, 3,";
        String s = "张三, 李四";
        String[] ir = i.split(",");
        String[] name = s.split(",");
        System.out.println(name.length);
//        for (int j = 0; j < ir.length; j++) {
//            System.out.println(ir[j]);
//            System.out.println(name.length >= j + 1 ? name[j] : null);
//        }

        String one = "12313123";
        String[] split = one.split(",");
        for (int i1 = 0; i1 < split.length; i1++) {
            System.out.println(split[i1]);
        }
        User user = new User();
        user.setAge(1);
        user.setSex("nan");
        user.setName(1 < 2 ? "zhangsan" : null);

//        System.out.println(user.getName() != null ? user.getName() : user.getAge());
        Set<String> whiteList = WhiteUtil.getWhiteList();
        System.out.println("!!!!  " + whiteList);
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(",");
        String sex = user.getSex();
        if (CollectionUtils.isNotEmpty(whiteList) && !whiteList.contains("12313123")) {
            sex = sex.charAt(0) + "***" + sex.charAt(sex.length() - 1);
        }
        sb.append(sex);
        System.out.println(sb);

        MtTaskStatusPeriodEnum byLable = MtTaskStatusPeriodEnum.getByLable(4);
    }
}

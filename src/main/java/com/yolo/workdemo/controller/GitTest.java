package com.yolo.workdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yolo.workdemo.common.AjaxResult;
import com.yolo.workdemo.domain.User;
import com.yolo.workdemo.util.IdConvertUtil;
import com.yolo.workdemo.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("size")
@Slf4j
public class GitTest {

    public static void main(String[] args) throws Exception {

        System.out.println("分支一尝试第2次提交");
        System.out.println("分支二提交1");
        String id = "201139431";
        Long realId = IdConvertUtil.getRealId(id, null);
        System.out.println(realId);
        Long realId1 = IdConvertUtil.getRealId("20113943", null);
        System.out.println(realId1);

        AtomicInteger successUserNumCount = new AtomicInteger(0);
        successUserNumCount.addAndGet(Integer.valueOf(1));
        System.out.println("=============" + successUserNumCount);
        Long t = 123176266L;

//        Long l = t == 0 ? 0 : Long.parseLong(String.valueOf(NumberUtils.toScaledBigDecimal(t * 1d / 100, 2, RoundingMode.HALF_UP).doubleValue()));
//        System.out.println(l);
        String taskId = "1,";
        String[] split = taskId.split(",");
        System.out.println(split.length);
        ArrayList<String> strings = new ArrayList<>();
        List<String> strings1 = Arrays.asList(split);
        for (String s : strings1) {
            System.out.println("打印=" + s + "=");
            strings.add(s);
        }
        Assert.isTrue(CollectionUtils.isNotEmpty(strings), "不能为空");

        Assert.isTrue(1 < 2 || 2 > 3, "至少要有一个条件满足");
        System.out.println("-------");
        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 21);
        User wangwu = new User("王五", "女", 23);
        List<User> list = new ArrayList<>();
        if (!CollectionUtils.isNotEmpty(list)) {
            System.out.println("当前list为空" + list.size());
        }
        list.add(lisi);
        list.add(wangwu);
        list.add(zhangsan);
        System.out.println("当前list长度" + list.size());
        Assert.isTrue(list.size() <= 2, "批量导出用户明细最多可选2个任务");

//        list.stream().collect(Collectors.groupingBy(User::getAge))
//                .forEach((key, value) -> {
//                    if (value.size() > 1) {
//                        System.out.println("长度：" + value.size());
//                        List<User> sorted = value.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
//                        System.out.println("排序后：" + sorted);
////                        set.add(sorted.get(0));
//                    } else {
////                        set.add(value.get(0));
//                    }
//                });
//        list.forEach(user -> {
//            user.setAge(user.getAge() * 2);
//        });
        System.out.println("========排序前=======");
        System.out.println(list);

        System.out.println("========排序后=======");
        ListUtils.sort(list, false, "age");
        System.out.println(list);

        Class<User> clazz = User.class;
        User obj = clazz.newInstance();

        // 获取对象属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String upperChar = fieldName.substring(0, 1).toUpperCase();
            String anotherStr = fieldName.substring(1);
            String methodName = "get" + upperChar + anotherStr;
            System.out.println(methodName);
//            Method method = clazz.getMethod(methodName, new Class[]{});
//            method.setAccessible(true);
//            Object resultValue = method.invoke(obj, new Object[]{});
//            // 这里可以编写你的业务代码
//            System.out.println(fieldName + ": " + resultValue);
        }
        list.stream().sorted(Comparator.comparing(User::getAge).reversed())
                .collect(Collectors.toList());

        log.info("关键字类型构造器接受字段为" + JSONObject.toJSONString(list));
        System.out.println("==============");
        Map<Integer, User> map = list.stream().collect(Collectors.toMap(User::getAge, User -> User));
        System.out.println(map);
        String group = StringUtils.joinWith("==", "wang", "zhan");
        System.out.println(group);
        HashSet<String> set = new HashSet<>();
        set.add("wz");
        set.add("db");
        set.add("yolo");
        set.forEach(value -> {
            Assert.isTrue(value.length() <= 20, "关键词超过了20个字符");
            log.info(value);
        });
        log.info("关键字类型构造器接受字段为" + JSONObject.toJSONString(set));

        JSONObject valueJson = JSONObject.parseObject("{\"insctBy\":0,\"value\":\"手机充电器\"}");
        Integer insctBy = valueJson.getInteger("insctBy");
        String value = valueJson.getString("value");

        System.out.println(insctBy);
        System.out.println(value);
    }

    @GetMapping
    public AjaxResult testSize(@Validated User user) {

        System.out.println("==============" + user.toString());
        return AjaxResult.success();
    }

    //    @RateLimit(code = "all", permitsPerMinute = 10, permitsPerDay = 500)
    @GetMapping("all")
    public AjaxResult get() {
        System.out.println("==============");
        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 21);
        User wangwu = new User("王五", "女", 23);
        List<User> list = new ArrayList<>();
        if (!CollectionUtils.isNotEmpty(list)) {
            System.out.println("当前list为空" + list.size());
        }
        list.add(lisi);
        list.add(wangwu);
        list.add(zhangsan);
        System.out.println("当前list长度" + list.size());
        Assert.isTrue(list.size() <= 3, "批量导出用户明细最多可选3个任务");
        return AjaxResult.success();
    }
}

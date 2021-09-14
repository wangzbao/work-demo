package com.yolo.workdemo.common.constants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
public class CsvHeadConstant {
    public static HashMap taskListmap = new HashMap<String, String>();
    public static HashMap<String, Boolean> taskFieldHeadRelationmap = new HashMap<String, Boolean>();

    /**
     * 获取页面选中的表头
     *
     * @return 灵活获取表头
     */
    public static String[] getHead(String serialNum) {
        List<String> serialList = Arrays.asList(serialNum.split(","));
        StringBuffer sb = new StringBuffer();
        for (String num : serialList) {
            sb.append(taskListmap.get(num)).append(",");
        }
        String result = sb.toString();
        //再次初始化字段表头关系
        FieldHeadRelationMapInit();
        updateFieldHeadRelation(serialList, taskFieldHeadRelationmap);
        String substring = result.substring(0, result.length() - 1);
        List<String> list = Arrays.asList(substring.split(","));
        return list.toArray(new String[0]);
    }

    //全量表头初始化
    @Bean
    public static HashMap taskListMapInit() {
        taskListmap.put("1", "姓名");
        taskListmap.put("2", "性别");
        taskListmap.put("3", "年龄");
        taskListmap.put("4", "字段4");
        taskListmap.put("5", "字段5");
        taskListmap.put("6", "字段6");
        taskListmap.put("7", "字段7");
        taskListmap.put("8", "字段8");
        taskListmap.put("9", "字段9");
        taskListmap.put("10", "字段10");
        return taskListmap;
    }

    //标头名称和字段是否展示映射关系
    @Bean
    public static HashMap FieldHeadRelationMapInit() {
        taskFieldHeadRelationmap.put("1", false);
        taskFieldHeadRelationmap.put("2", false);
        taskFieldHeadRelationmap.put("3", false);
        taskFieldHeadRelationmap.put("4", false);
        taskFieldHeadRelationmap.put("5", false);
        taskFieldHeadRelationmap.put("6", false);
        taskFieldHeadRelationmap.put("7", false);
        taskFieldHeadRelationmap.put("8", false);
        taskFieldHeadRelationmap.put("9", false);
        taskFieldHeadRelationmap.put("10", false);
        return taskFieldHeadRelationmap;
    }

    public static HashMap<String, Boolean> FieldHeadRelation() {
        return taskFieldHeadRelationmap;
    }

    private static void updateFieldHeadRelation(List<String> list, HashMap taskFieldHeadRelationmap) {
        for (String num : list) {
            taskFieldHeadRelationmap.put(num, true);
        }
    }

    public static void main(String[] args) {
        taskListMapInit();
        String[] head = getHead("1,2,4,6,9");
        System.out.println(taskFieldHeadRelationmap);
    }


}

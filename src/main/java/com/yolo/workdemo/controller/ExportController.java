package com.yolo.workdemo.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelFileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yolo.workdemo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class ExportController {

    @GetMapping("export")
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        ExcelWriter writer = new ExcelWriter(true, "第1个sheet");

        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 25);
        User wangwu = new User("王五", "女", 21);

        ArrayList<User> list = new ArrayList<>();
        list.add(zhangsan);
        list.add(lisi);
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("sex","性别");
        writer.addHeaderAlias("age","年龄");
        writer.write(list);

        ExcelWriter sheet1 = writer.setSheet("第二个sheet");
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(lisi);
        list1.add(wangwu);
        sheet1.addHeaderAlias("name","姓名1");
        sheet1.addHeaderAlias("sex","性别1");
        sheet1.addHeaderAlias("age","年龄1");
        sheet1.write(list1);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//name是下载对话框的名称，不支持中文，想用中文名称需要进行utf8编码
        String excelName = "用户基本信息表";
//excelName = new String(excelName.getBytes(),"utf-8");
        excelName = URLEncoder.encode(excelName, "utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");

//将excel文件信息写入输出流，返回给调用者
        ServletOutputStream excelOut = null;
        try {
            excelOut = response.getOutputStream();
            writer.flush(excelOut, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(excelOut);
    }

    public static void main(String[] args) {
        ExcelWriter writer = new ExcelWriter(true, "第1个sheet");
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        map.put("姓名", "张三");
        map.put("性别", "男");
        map.put("年龄", "18");

        map1.put("姓名", "李四");
        map1.put("性别", "女");
        map1.put("年龄", "25");
        ArrayList<Object> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        writer.write(list, true);
        writer.close();
    }
}

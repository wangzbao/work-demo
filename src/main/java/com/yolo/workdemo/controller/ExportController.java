package com.yolo.workdemo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.yolo.workdemo.common.constants.CsvHeadConstant;
import com.yolo.workdemo.domain.NewCustomerParam;
import com.yolo.workdemo.domain.Sheet2;
import com.yolo.workdemo.domain.User;
import com.yolo.workdemo.util.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/excel")
@Slf4j
public class ExportController {

    //投放执行表
    private static String[] releaseExecutionHead = {"任务名", "执行日期", "品类", "目的", "品牌", "投放策略", "圈选条件", "投放时间",
            "投放渠道", "文案", "预估投放量", "投放上线", "预估订单", "预估ROI", "执行人", "备注"};
    //竞品分析
    private static String[] conpetitiveProctAnalysisHead = {"SKU", "品类", "类型", "品牌", "标题", "价格", "竞争度"};

    @PostMapping("export")
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        ExcelWriter writer = new ExcelWriter(true, "第1个sheet");
        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 25);
        User wangwu = new User("王五", "女", 21);

        ArrayList<User> list = new ArrayList<>();
        list.add(zhangsan);
        list.add(lisi);
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("head", "预估投放量");
        writer.addHeaderAlias("head1", "投放上线");
        writer.write(list);

        ExcelWriter sheet2 = writer.setSheet("第二个sheet");
        Sheet2 x = new Sheet2("sku1", "品类1");
        Sheet2 x1 = new Sheet2("sku2", "品类2");
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(x);
        list1.add(x1);
        sheet2.addHeaderAlias("sku", "sku");
        sheet2.addHeaderAlias("category", "品类");
        sheet2.write(list1);
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
//        return AjaxResult.success();
    }

    @GetMapping("selected")
    public void selected(HttpServletResponse response, String head) throws IOException {
        String[] s = CsvHeadConstant.getHead(head);
        User zhangsan = new User("张三", "男", 18);
        User lisi = new User("李四", "女", 25);
        User wangwu = new User("王五", "女", 21);
        List<User> list = new ArrayList<>();
        list.add(zhangsan);
        list.add(lisi);
        list.add(wangwu);
        List<Object[]> lines = new ArrayList<>();
        //添加表头
        lines.add(s);
        HashMap<String, Boolean> relationMap = CsvHeadConstant.FieldHeadRelation();
        list.forEach(user -> {
            List<Object> line = new ArrayList<>();
            if (relationMap.get("1")) {
                line.add(user.getName());
            }
            if (relationMap.get("2")) {
                line.add(user.getSex());
            }
            if (relationMap.get("3")) {
                line.add(user.getAge());
            }
            if (relationMap.get("4")) {
                line.add(user.getName());
            }
            lines.add(line.toArray());
        });
        CSVUtils.responseCSV("测试动态下载", lines, response);
    }


    @GetMapping("/list")
    public String list(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dtFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtTo) {
        JSONObject object = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("dtFrom", DateUtil.format(dtFrom, "yyyy-MM-dd"));
        json.put("dtTo", DateUtil.format(dtTo, "yyyy-MM-dd"));
        json.put("period", 1);
        json.put("days", 1);

        object.put("methodType", 1);
        object.put("param", json);
        com.alibaba.fastjson.JSONObject jsonParam = JSON.parseObject(object.toString());
        log.info(jsonParam.toString());
        if (!jsonParam.containsKey("methodType")) {
            log.info("json有问题");
        }
        String param = jsonParam.getString("param");
        NewCustomerParam newCustomerParam = JSON.parseObject(jsonParam.getString("param"), NewCustomerParam.class);
        log.info(param);
        log.info(newCustomerParam.toString());
        return "你是真的秀";
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

package com.yolo.workdemo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.fastjson.JSON;
import com.yolo.workdemo.controller.TestController;
import com.yolo.workdemo.domain.*;
import com.yolo.workdemo.domain.enums.StatsTypeEnum;
import com.yolo.workdemo.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkDemoApplication.class)
@Slf4j
class WorkDemoApplicationTests {
    //颜色
    private Short colorIndex;
    private User user = new User();
    private static final List<String> NOLIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Test
    void contextLoadsTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
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

    @Test
    public void list() {
//        String keyword = "iphone13,xiaomi12,oppofindx3";
        String keyword = "";

        String[] split = keyword.split(",");
        List<String> list = new ArrayList<>(split.length);
        if (0 == split.length) {
            new ArrayList<>();
        }
        CollectionUtils.addAll(list, split);
        System.out.println("=======");

        log.debug("关键字是：{}", list);

    }

    @Test
    public void no() {
        User userA = new User();
        userA.setNo("A");
        User userB = new User();
        userB.setNo("B");
        User userC = new User();
//        userB.setNo("C");
        ArrayList<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        list.add(userC);
        List<User> collect1 = list.stream().filter(data -> StringUtils.isNotEmpty(data.getNo())).collect(Collectors.toList());
        log.info("collect1：{}", collect1);
        List<String> collect = list.stream().map(data -> data.getNo()).collect(Collectors.toList());

        log.info("list：{}", collect);
        log.info("list的长度：{}", list.size());
        if (CollectionUtils.isNotEmpty(list) && list.size() >= 26) {
            System.out.println("商家最多可以创建26个人群画像模板");
        }
        // 获取当前库中最大编号
        if (CollectionUtils.isNotEmpty(list)) {
            System.out.println("新的编号" + NOLIST.get(list.size()));
            System.out.println("新的编号" + NOLIST.get(list.size() + 1));
        }
    }

    @Test
    public void npe() {
        int i = 2;
        User userA = new User();
        if (i == 1) {
            userA = new User("张三", "男", 14);
        }
        int type = 2;
        String keyword = "weqre";
        Date date = DateTime.now().toJdkDate();
        Date date1 = DateTime.of(1645008611).toJdkDate();
        int compare = DateUtil.compare(date, date1);
        System.out.println("====" + compare);

        boolean existCrowdPackageDifferent;
        boolean existLabelDifferent = false;
        boolean existIntelDifferent = true;
        existCrowdPackageDifferent = existLabelDifferent || existIntelDifferent ? true : false;

        if (!existCrowdPackageDifferent && 1 == 1) {
            System.out.println(1);
            return;
        } else if (1 == 1) {
            System.out.println(2);
        }

        boolean notBlank = StringUtils.isNotBlank("12345678912345678912");
        System.out.println("===" + notBlank);
        System.out.println(existCrowdPackageDifferent);
        if (2 == type && NumberUtil.isLong(keyword)) {
            System.out.println("人群包类型仅支持数字");
        }
//        Assert.
        System.out.println("~~~~~~~~~~~~~~~~" + userA.getNo());
    }

    @Test
    public void contains() {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> listDb = new ArrayList<>();
        User user = new User("张三", "男", 18);
        user.setNo("1");
        User user1 = new User("张三", "男", 18);

//        Integer i = 500002;
//        if (i != null){
//            Assert.isTrue(i < 500001, "最大发送上线不能超过50w");
//        }
        list.add(user);
        listDb.add(user1);

        List<User> collect = list.stream().filter(data -> !listDb.contains(data)).collect(Collectors.toList());
        System.out.println("====" + collect);
    }

    @Test
    public void t() {
//        User user = new User();
//        user.setNo("v2");
//        User user1 = new User();
//        Assert.isTrue(user.getAge() != null, "参数非法");
//        user1.setHistory(StringUtils.isBlank(user.getNo()) ? true : !user.getNo().equals("v2"));
//        log.info("~~~~~~" + user1.toString());
        ErrorCode[] values = ErrorCode.values();
        ErrorCode[] values1 = ErrorCode.values();
        for (ErrorCode value : values1) {
            System.out.println(value);
            if (value.name().equals("WAIT_TIME_OUT")) {
                System.out.println(value.getDesc());
            }
        }
    }

    @Test
    public void inorderTraversal() {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();


    }

    // 先序
    void getDataIn(TreeNode root, List list) {
        if (root == null) {
            list.add(null);
            return;
        }
        list.add(root.val);
        getDataIn(root.left, list);
        getDataIn(root.right, list);
    }


    // 中序
    void getDataPre(TreeNode root, List list) {
        if (root == null) {
            list.add(null);
            return;
        }
        getDataPre(root.left, list);
        list.add(root.val);
        getDataPre(root.right, list);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private void updateNodeCfg(Long id, User user, String requestId, Integer retryCount) {
        log.info("保存画布节点执行结果1:{}", JSON.toJSONString(user));
        ExcelVo node = new ExcelVo();
        node.setId(id);
        // 保存config
        if (null == user) {
            user = new User();
            log.info("保存画布节点执行结果2:{}", JSON.toJSONString(user));
        }
        user.setAge(retryCount);
        System.out.println(JSON.toJSONString(user));
        log.info("保存画布节点执行结果3:{}", JSON.toJSONString(user));
        log.info("保存画布节点执行结果:{}", JSON.toJSONString(node));
    }

    @Test
    public void T() {
        updateNodeCfg(1L, null, "1", 1);

    }

    public static void main(String[] args) {
        int index = 1;
        int foo[] = new int[3];
        int bar = foo[index];
        int value = bar + index;
        System.out.println(value);
    }

    @Test
    public void easyExcel() throws FileNotFoundException {
        // 输出流
        OutputStream outputStream = null;
        File file = new File("E:" + File.separator + "sheet.xlsx");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 创建一个输出流，写入到设置的路径中。
        outputStream = new FileOutputStream(file);

        ExcelWriter excelWriter = null;
        List<Sheet1> sheets1 = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Sheet1 sheet1 = new Sheet1();
            sheet1.setAge(i);
            sheet1.setName("name" + i);
            sheet1.setSchool("school" + i);
            sheet1.setSchoolNew("schoolNew" + i);
            sheet1.setSuccess("success" + i);
            sheet1.setSuccessNew("successNew" + i);
            sheet1.setDdDeductNum(i);
            sheet1.setDdDeductNumNew(i);
            sheet1.setDdReadNum(i);
            sheet1.setDdReadNumNew(i);
            sheet1.setSuccessSmsTimes(i);
            sheet1.setSuccessSmsTimesNew(i);
            sheet1.setSmsDeductNum(i);
            sheet1.setSmsDeductNumNew(i);
            sheet1.setShortLinkClickNum(i);
            sheet1.setShortLinkClickNumNew(i);
            sheets1.add(sheet1);
        }
        sheets1.add(new Sheet1());

        for (int i = 0; i < sheets1.size(); i++) {
            System.out.println(sheets1.get(i));
        }
        List<Sheet2> sheets2 = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Sheet2 sheet2 = new Sheet2();
            sheet2.setSku("sku" + i);
            sheet2.setCategory("category" + i);
            sheets2.add(sheet2);
        }
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("任务id");
        List<String> list1 = new ArrayList<>();
        list1.add("任务名称");
        lists.add(list);
        lists.add(list1);

        List<List<String>> lists11 = new ArrayList<>();
        List<String> list11 = new ArrayList<>();
        list11.add("姓名");
        List<String> list12 = new ArrayList<>();
        list12.add("年龄");
        lists11.add(list11);
        lists11.add(list12);
        try {
            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(outputStream).build();
            // 这里注意 如果同一个sheet只要创建一次

            // 根据用户传入字段 假设我们要忽略 date
            Set<String> excludeColumnFiledNames = new HashSet<String>();
//            excludeColumnFiledNames.add("school");
//            excludeColumnFiledNames.add("age");

            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "模板1").build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "模板2").head(Sheet2.class).build();

//            .needHead(Boolean.TRUE).build();
            WriteTable writeTable0 = EasyExcel.writerTable(0).excludeColumnFiledNames(excludeColumnFiledNames).head(Sheet1.class).needHead(true).build();
            WriteTable writeTable1 = EasyExcel.writerTable(1).head(Collections.singletonList(Collections.singletonList("说明：2022年6月前的数据，可能存在明细功能上线前，部分任务无日期明细情况"))).needHead(true).build();

//            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//            WriteFont headWriteFont = new WriteFont();
//            headWriteFont.setFontName("宋体");
//            headWriteFont.setFontHeightInPoints((short) 14);
//            headWriteFont.setBold(true);
//            // 设置字体颜色
//            headWriteFont.setColor((short) 1);
//            headWriteCellStyle.setWriteFont(headWriteFont);
////            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            CellStyle cellStyle = StyleUtil.buildHeadCellStyle(null, headWriteCellStyle);
//            cell.setCellStyle(cellStyle);
//
            TableStyle style = new TableStyle();
            style.setTableHeadBackGroundColor(IndexedColors.RED);
            style.set
            writeTable1.setTableStyle(style);
            WriteTable writeTable2 = EasyExcel.writerTable(2).excludeColumnFiledNames(excludeColumnFiledNames).head(Sheet1.class).needHead(true).build();
            // 第一次写入会创建头
            excelWriter.write(sheets1, writeSheet1, writeTable0);
            // 第二次写如也会创建头，然后在第一次的后面写入数据
//            excelWriter.write(sheets2, writeSheet1, writeTable1);
            excelWriter.write(null, writeSheet1, writeTable1);

            excelWriter.write(sheets1, writeSheet1, writeTable2);
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
//            excelWriter.write(sheets1, writeSheet1);
            excelWriter.write(sheets2, writeSheet2);

        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

    }

    @Test
    public void map() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "一");
        System.out.println(map);
        map.computeIfAbsent("2", k -> "二");
        System.out.println(map);
        String s1 = map.get(3);
        System.out.println(s1);

        int k = 10;
        while (k > 0) {
            k = k - 1;
            System.out.println(k);
        }

        String s = 0 == 100 ? "0" : NumberUtils.toScaledBigDecimal(0 * 100d / 100, 1, RoundingMode.HALF_UP).toString();
        System.out.println(s);
    }

    @Test
    public void json() {
        StatsNodeCfg nodeCfg = new StatsNodeCfg();
        nodeCfg.setStatsType(1);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("111");
        strings.add("222");
        nodeCfg.setStatsItems(strings);
        String jsonString = JSON.toJSONString(nodeCfg);
        System.out.println(111);
        System.out.println(jsonString);

        StatsNodeCfg cfg = com.alibaba.fastjson.JSON.parseObject(jsonString, StatsNodeCfg.class);
        System.out.println(StatsTypeEnum.getByCode(cfg.getStatsType()).getName());
    }

    @Test
    public void t1() throws JSONException {
        String sku = "1,2,3,4,5,6,7,8,9,10";
        StringBuffer sb = new StringBuffer();
        for (String s : sku.split(",")) {
            sb.append("\"")
                    .append(s)
                    .append("\",");
        }
        System.out.println(sb.toString());
        String s = StringUtils.removeEnd(sb.toString(), ",");
        System.out.println(s);
        System.out.println("---------------------------");

        JSONObject object = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("type", 1);
        object.put("type", 2);
        object.put("param", json);

        System.out.println(object);
        String obj = JSON.toJSONString(object);
        System.out.println(object.toString());

        Long t = 1L;
        String st = JSON.toJSONString(t);
        Long rs = Long.valueOf(st);
        System.out.println(rs);
    }

    @Test
    public void jso() {
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan", "男", 18));
        list.add(new User("小明", "女", 20));
        Map<String, User> newMap = list.stream().collect(Collectors.toMap(data -> data.getName() + "_" + data.getAge(), Function.identity()));
        System.out.println(JSON.toJSONString(newMap));
        User user = newMap.get("小明" + "_" + 20);
        System.out.println(JSON.toJSONString(user));
    }

}

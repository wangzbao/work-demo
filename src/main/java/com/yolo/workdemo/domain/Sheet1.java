package com.yolo.workdemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class Sheet1 {
    @ExcelProperty(value = "姓名", order = 0)
    private String name;
    @ColumnWidth(15)
    @ExcelProperty(value = "年龄", order = 1)
    private Integer age;
    @ExcelProperty(value = {"目标学校", "总计"})
    private String school;

    @ExcelProperty(value = {"目标学校", "新客"})
    private String schoolNew;

    @ExcelProperty(value = {"发送", "总计"})
    private String success;

    @ExcelProperty(value = {"发送", "新客"})
    private String successNew;
    /**
     * 咚咚计费次数
     */
    @ExcelProperty(value = {"咚咚计费次数", "总计"})
    private Integer ddDeductNum;
    @ExcelProperty(value = {"咚咚计费次数", "新客"})
    private Integer ddDeductNumNew;
    /**
     * 短信发送次数
     */
    @ExcelProperty(value = {"短信发送次数", "总计"})
    private Integer successSmsTimes;
    @ExcelProperty(value = {"短信发送次数", "新客"})
    private Integer successSmsTimesNew;
    /**
     * 短信计费次数
     */
    @ExcelProperty(value = {"短信计费次数", "总计"})
    private Integer smsDeductNum;
    @ExcelProperty(value = {"短信计费次数", "新客"})
    private Integer smsDeductNumNew;

    /**
     * 短信链接点击次数
     */
    @ExcelProperty(value = {"短信链接点击次数", "总计"})
    private Integer shortLinkClickNum;
    @ExcelProperty(value = {"短信链接点击次数", "新客"})
    private Integer shortLinkClickNumNew;
    /**
     * 咚咚已读数
     */
    @ExcelProperty(value = {"咚咚已读数", "总计"})
    private Integer ddReadNum;
    @ExcelProperty(value = {"咚咚已读数", "新客"})
    private Integer ddReadNumNew;

    public Sheet1(String name, Integer age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolNew() {
        return schoolNew;
    }

    public void setSchoolNew(String schoolNew) {
        this.schoolNew = schoolNew;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccessNew() {
        return successNew;
    }

    public void setSuccessNew(String successNew) {
        this.successNew = successNew;
    }

    public Sheet1() {

    }
}

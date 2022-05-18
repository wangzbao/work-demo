package com.yolo.workdemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExcelVo {
    private Long id;
    private String number;
    private String name;
    private String company;
    private String groups;
    private String attendanceDays;
    private List<AttendanceDetail> attendanceDetail;
}

package com.yolo.workdemo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NewCustomerParam {

    /**
     * 回算周期
     */
    private Integer period;

    /**
     * 1-90天、2-180天
     */
    private Integer days;

    /**
     * 机器ID
     */
    private Long botId;

    /**
     * 任务ID
     */
    private String taskIds;

    /**
     * 开始日期
     */
    private Date dtFrom;

    /**
     * 结束日期
     */
    private Date dtTo;

    /**
     * 第几页
     */
    private Integer page = 1;

    /**
     * 每页多少条
     */
    private Integer pageSize = 10;
}

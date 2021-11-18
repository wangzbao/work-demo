package com.yolo.workdemo.domain;

import lombok.Data;

@Data
public class TaskStatsUserDetailVO {
    /**
     * 任务效果分析表dt
     */
    private String dt;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 数据回算周期
     */
    private Integer period;
}

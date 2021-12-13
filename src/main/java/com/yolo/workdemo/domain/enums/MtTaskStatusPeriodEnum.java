package com.yolo.workdemo.domain.enums;

import lombok.AllArgsConstructor;

/**
 * 效果分析周期枚举类
 */
@AllArgsConstructor
public enum MtTaskStatusPeriodEnum {

    PERIOD_ONE(1, "24小时"),

    PERIOD_SEVEN(2, "7天"),

    PERIOD_FIFTEEN(3, "15天"),

    PERIOD_THERE(4, "3天");


    private final Integer label;

    private final String desc;

    public Integer getLabel() {
        return label;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @param label
     * @return
     */
    public static MtTaskStatusPeriodEnum getByLable(int label) {
        for (MtTaskStatusPeriodEnum e : MtTaskStatusPeriodEnum.values()) {
            if (e.getLabel() == label) {
                return e;
            }
        }
        return null;
    }
}

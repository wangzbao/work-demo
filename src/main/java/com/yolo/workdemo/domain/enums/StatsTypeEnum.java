package com.yolo.workdemo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoyueming@jd.com
 * @date 2022/4/26
 */
@Getter
@AllArgsConstructor
public enum StatsTypeEnum {
    DEFAULT(0, "默认"),
    SKU(1, "商品"),
    CAT(2, "品类"),
    VENDER(3, "店铺");

    private int code;
    private String name;

    /**
     * @param code
     * @return
     */
    public static StatsTypeEnum getByCode(Integer code) {
        if (null != code) {
            for (StatsTypeEnum e : StatsTypeEnum.values()) {
                if (e.getCode() == code) {
                    return e;
                }
            }
        }
        return null;
    }
}

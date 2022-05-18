package com.yolo.workdemo.domain;

import lombok.Data;

import java.util.List;

/**
 * 数据分析节点配置
 *
 * @author zhaoyueming@jd.com
 * @date 2022/4/26
 */
@Data
public class StatsNodeCfg {

    /**
     * 回算维度，如默认、商品、品类等
     *
     * @see StatsTypeEnum#code
     */
    private Integer statsType;

    /**
     * 回算维度值列表，如商品ID、一/二/三级品类ID等
     */
    private List<String> statsItems;
}

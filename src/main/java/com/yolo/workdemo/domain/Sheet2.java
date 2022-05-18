package com.yolo.workdemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Sheet2 {
    @ExcelProperty(value = "sku", index = 0)
    private String sku;
    @ExcelProperty(value = "品类", index = 1)
    private String category;
//    @ExcelProperty(value = "品类新", index = 1)
//    private String categoryNew;

    public Sheet2(String sku, String category) {
        this.sku = sku;
        this.category = category;
    }

    public Sheet2() {

    }
}


package com.yolo.workdemo.domain;

import lombok.Data;

@Data
public class Sheet2 {
    private String sku;
    private String category;

    public Sheet2(String sku, String category) {
        this.sku = sku;
        this.category = category;
    }
}


package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductPageQueryDTO implements Serializable {

    //商品名称
    private String name;

    //售卖状态
    private Integer status;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}

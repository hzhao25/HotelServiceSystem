package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDTO implements Serializable {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String image;

    //0 停售 1 起售
    private Integer status;

}

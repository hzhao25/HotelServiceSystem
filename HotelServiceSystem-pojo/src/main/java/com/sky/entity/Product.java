package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    //商品名称
    private String name;

    //商品价格
    private BigDecimal price;

    //商品库存
    private Integer stock;

    //商品图片
    private String image;

    //商品售卖状态(0 停售 1 起售)
    private Integer status;

    //商品创建时间
    private LocalDateTime createdAt;

    //商品更新时间
    private LocalDateTime updatedAt;

    //商品创建者
    private Long createUser;

    //商品更新者
    private Long updateUser;
}

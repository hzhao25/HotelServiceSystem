package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 消耗品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consume implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    //消耗品名称
    private String name;

    //消耗品库存
    private Integer stock;

    //消耗品图片
    private String image;

    //消耗品售卖状态(0 停售 1 起售)
    private Integer status;

    //消耗品创建时间
    private LocalDateTime createdAt;

    //消耗品更新时间
    private LocalDateTime updatedAt;

    //消耗品创建者
    private Long createUser;

    //消耗品更新者
    private Long updateUser;
}

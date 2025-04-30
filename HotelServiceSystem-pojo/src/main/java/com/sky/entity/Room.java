package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客房
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    //客房名称
    private String name;

    //客房库存
    private Long userId;

    //客房图片
    private String qrCode;

    //客房售卖状态(0 停售 1 起售)
    private Integer status;

    //客房创建时间
    private LocalDateTime createdAt;

    //客房更新时间
    private LocalDateTime updatedAt;

    //客房创建者
    private Long createUser;

    //客房更新者
    private Long updateUser;
}

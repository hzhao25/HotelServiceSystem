package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //类型: 1菜品分类 2套餐分类
    private String type;

    //分类名称
    private String name;

    //顺序
//    private Integer sort;

    //分类状态 0标识禁用 1表示启用
    private Integer status;

    //开始服务时间
    private Time startTime;

    //结束服务时间
    private Time endTime;

    //创建时间
    private LocalDateTime createdAt;

    //更新时间
    private LocalDateTime updatedAt;

    //创建人
    private Long createUser;

    //修改人
    private Long updateUser;


}

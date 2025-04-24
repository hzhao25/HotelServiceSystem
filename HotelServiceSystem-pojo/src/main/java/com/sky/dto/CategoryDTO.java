package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
public class CategoryDTO implements Serializable {

    //主键
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    private String type;

    //分类名称
    private String name;

    //排序
//    private Integer sort;

    //开始服务时间
    private Time startTime;

    //结束服务时间
    private Time endTime;
}

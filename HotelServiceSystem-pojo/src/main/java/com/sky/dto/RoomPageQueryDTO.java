package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomPageQueryDTO implements Serializable {

    //客房名称
    private String name;

    //客房状态
    private Integer status;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}

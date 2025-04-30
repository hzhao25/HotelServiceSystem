package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomDTO implements Serializable {

    private Integer id;

    private String name;

    //0 忙碌 1 空闲
    private Integer status;
}

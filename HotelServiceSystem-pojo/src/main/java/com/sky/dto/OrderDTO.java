package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private Long userId;

    private String type;

    private String description;

    private String image;

    private LocalDateTime bookTime;

    //0紧急1正常
    private int emergency;

    private BigDecimal price;

    private String status;
}

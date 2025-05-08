package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roomId;

    private Long userId;

    private Long staffId;

    private String type;

    private String description;

    private String image;

    private LocalDateTime bookTime;

    private LocalDateTime completedTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //0紧急1正常
    private int emergency;

    private BigDecimal price;

    private String status;
}

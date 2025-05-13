package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roomId;

    private String roomName;

    private Long userId;

    private String username;

    private Long phone;

    private Long staffId;

    private String staffName;

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

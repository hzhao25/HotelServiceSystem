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
public class OrderPageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roomId;

    private String username;

    private Long phone;

    private String description;

    private String image;

    private LocalDateTime bookTime;

    //0紧急1正常
    private int emergency;

    private BigDecimal price;

    private String status;
}

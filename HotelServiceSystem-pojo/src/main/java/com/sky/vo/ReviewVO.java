package com.sky.vo;

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
public class ReviewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long orderId;

    private String type;

    private Long staffId;

    private Integer rating;

    private String comment;

    private LocalDateTime createdAt;

    private Long userId;
}

package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO implements Serializable {

    private Long orderId;

    private String type;

    private Long staffId;

    private Integer rating;

    private String comment;

    private LocalDateTime createdAt;

    private Long userId;
}

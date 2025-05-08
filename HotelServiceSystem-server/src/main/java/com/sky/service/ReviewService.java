package com.sky.service;

import com.sky.dto.OrderDTO;
import com.sky.dto.ReviewDTO;
import com.sky.vo.OrderVO;
import com.sky.vo.ReviewVO;

import java.util.List;

public interface ReviewService {

    /**
     * 订单生成
     * @param reviewDTO
     * @return
     */
    void save(ReviewDTO reviewDTO);

    /**
     * 查询员工的所有评论
     * @return
     */
    List<ReviewVO> selectByEmployeeId(Long staffId);

    /**
     * 查询用户的所有评论
     * @param userId
     * @return
     */
    List<ReviewVO> selectByUserId(Long userId);
}

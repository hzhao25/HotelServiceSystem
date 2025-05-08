package com.sky.service.impl;

import com.sky.constant.OrderConstant;
import com.sky.dto.OrderDTO;
import com.sky.dto.ReviewDTO;
import com.sky.entity.Employee;
import com.sky.entity.Order;
import com.sky.mapper.EmployeeMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.ReviewMapper;
import com.sky.mapper.RoomMapper;
import com.sky.service.OrderService;
import com.sky.service.ReviewService;
import com.sky.vo.OrderVO;
import com.sky.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 订单生成
     * @param reviewDTO
     * @return
     */
    public void save(ReviewDTO reviewDTO) {
        reviewDTO.setCreatedAt(LocalDateTime.now());
        reviewMapper.save(reviewDTO);
    }

    /**
     * 查询员工评论
     * @param staffId
     * @return
     */
    public List<ReviewVO> selectByEmployeeId(Long staffId) {
        List<ReviewVO> list=reviewMapper.selectByEmployeeId(staffId);
        return list;
    }

    /**
     * 查询用户评论
     * @param userId
     * @return
     */
    public List<ReviewVO> selectByUserId(Long userId) {
        List<ReviewVO> list=reviewMapper.selectByUserId(userId);
        return list;
    }
}

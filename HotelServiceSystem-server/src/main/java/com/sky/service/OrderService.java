package com.sky.service;

import com.sky.dto.OrderDTO;
import com.sky.vo.OrderVO;

import java.util.List;

public interface OrderService {

    /**
     * 订单生成
     * @param orderDTO
     * @return
     */
    void save(OrderDTO orderDTO);

    /**
     * 查询用户订单
     * @param userId
     * @return
     */
    List<OrderVO> selectOrderByUserId(Long userId);

    /**
     * 查询员工订单
     * @param staffId
     * @return
     */
    List<OrderVO> selectOrderByStaffId(Long staffId);


    /**
     * 取消订单
     * @param id
     * @param status
     */
    void updateStatus(String status, Long id);
}

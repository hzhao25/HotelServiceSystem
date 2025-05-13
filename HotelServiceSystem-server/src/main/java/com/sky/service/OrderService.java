package com.sky.service;

import com.sky.dto.OrderDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Order;
import com.sky.result.PageResult;
import com.sky.vo.OrderDetailVO;
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
     * @param order
     */
    void updateStatus(Order order);

    /**
     * 订单分页查询
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据订单id查询订单详细信息
     * @param id
     * @return
     */
    OrderDetailVO selectByOrderId(Integer id);
}

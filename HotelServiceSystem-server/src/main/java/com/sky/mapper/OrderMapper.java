package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Order;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 生成订单
     * @param order
     */
    void insert(Order order);

    /**
     * 查询用户订单
     * @param userId
     * @return
     */
    @Select("select * from `order` where user_id=#{userId}")
    List<OrderVO> selectOrderByUserId(Long userId);

    /**
     * 查询员工订单
     * @param staffId
     * @return
     */
    @Select("select * from `order` where staff_id=#{staffId}")
    List<OrderVO> selectOrderByStaffId(Long staffId);

    /**
     * 取消订单
     * @param id
     * @param status
     */
    void updateStatus(String status, Long id);
}

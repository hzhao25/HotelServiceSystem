package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.OrderDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Order;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.OrderService;
import com.sky.vo.DishVO;
import com.sky.vo.OrderDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理
 */
@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Api(tags = "订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单分页查询
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("订单分页查询")
    public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("订单分页查询：{}",ordersPageQueryDTO);
        PageResult pageResult=orderService.pageQuery(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询订单的详细信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("根据订单id查询订单详细信息")
    public Result<OrderDetailVO> selectByOrderId(Integer id){
        OrderDetailVO orderDetailVO=orderService.selectByOrderId(id);
        return Result.success(orderDetailVO);
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateStatus/{status}")
    @ApiOperation("订单状态更新")
    public Result updateStatus( @PathVariable("status") String status,Long id){
        Order order= new Order();
        order.setId(id);
        order.setStatus(status);
        orderService.updateStatus(order);
        return Result.success();
    }
}

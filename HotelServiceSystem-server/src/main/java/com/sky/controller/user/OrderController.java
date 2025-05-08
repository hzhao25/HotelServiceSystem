package com.sky.controller.user;

import com.sky.dto.OrderDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/order")
@Api(tags = "C端订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     *  订单生成
     * @param orderDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("C端订单生成")
    public Result save(@RequestBody OrderDTO orderDTO){
        log.info("订单：{}",orderDTO);

        orderService.save(orderDTO);

        return Result.success();
    }

    /**
     * 取消订单
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/cancel/{status}")
    @ApiOperation("C端订单取消")
    public Result cancel( @PathVariable("status") String status,Long id){
        orderService.updateStatus(status,id);
        return Result.success();
    }
}

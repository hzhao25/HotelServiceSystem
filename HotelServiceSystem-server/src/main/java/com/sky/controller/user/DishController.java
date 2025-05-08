package com.sky.controller.user;

import com.sky.entity.Consume;
import com.sky.entity.Dish;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@Slf4j
@RequestMapping("/user/dish")
@Api(tags = "C端菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="dish";

    @GetMapping
    @ApiOperation("C端查询所有菜品信息")
    public Result select(){
        //查询redis中是否存在消耗品数据
        List<DishVO> dishList=(List<DishVO>) redisTemplate.opsForValue().get(KEY);
        if(dishList!=null && dishList.size()>0){
            //如果redis存在菜品数据，直接返回，不用查数据库
            return Result.success(dishList);
        }
        //如果不存在，查数据库，再把消耗品数据放到redis里
        dishList=dishService.select();
        redisTemplate.opsForValue().set(KEY,dishList);

        return Result.success(dishList);
    }
}

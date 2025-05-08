package com.sky.controller.user;

import com.sky.entity.Consume;
import com.sky.result.Result;
import com.sky.service.ConsumeService;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userConsumeController")
@Slf4j
@RequestMapping("/user/consume")
@Api(tags = "C端消耗品相关接口")
public class ConsumeController {

    @Autowired
    private ConsumeService consumeService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="consume";

    @GetMapping
    @ApiOperation("C端查询所有消耗品信息")
    public Result select(){
        //查询redis中是否存在消耗品数据
        List<Consume> consumeList=(List<Consume>) redisTemplate.opsForValue().get(KEY);
        if(consumeList!=null && consumeList.size()>0){
            //如果redis存在菜品数据，直接返回，不用查数据库
            return Result.success(consumeList);
        }

        //如果不存在，查数据库，再把消耗品数据放到redis里
        consumeList=consumeService.select();
        redisTemplate.opsForValue().set(KEY,consumeList);

        return Result.success(consumeList);
    }
}

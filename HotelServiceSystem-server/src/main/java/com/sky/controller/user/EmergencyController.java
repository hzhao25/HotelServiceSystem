package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "C端紧急呼叫相关接口")
@RequestMapping("/user/emergency")
public class EmergencyController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="EMERGENCY";

    @GetMapping("/select")
    @ApiOperation("C端获取紧急呼叫按钮")
    public Result select(){
        String emergency= (String) redisTemplate.opsForValue().get(KEY);
        return Result.success(emergency);
    }

}

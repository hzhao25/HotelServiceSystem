package com.sky.controller.user;

import com.sky.entity.Product;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.ProductService;
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

@RestController("userProductController")
@Slf4j
@RequestMapping("/user/product")
@Api(tags = "C端商品相关接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="product";

    @GetMapping
    @ApiOperation("C端查询所有商品信息")
    public Result select(){
        //查询redis中是否存在消耗品数据
        List<Product> productList=(List<Product>) redisTemplate.opsForValue().get(KEY);
        if(productList!=null && productList.size()>0){
            //如果redis存在菜品数据，直接返回，不用查数据库
            return Result.success(productList);
        }
        //如果不存在，查数据库，再把消耗品数据放到redis里
        productList=productService.select();
        redisTemplate.opsForValue().set(KEY,productList);

        return Result.success(productList);
    }
}

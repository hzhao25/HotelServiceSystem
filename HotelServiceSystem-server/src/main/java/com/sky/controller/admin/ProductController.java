package com.sky.controller.admin;

import com.sky.dto.ProductDTO;
import com.sky.dto.ProductPageQueryDTO;
import com.sky.entity.Product;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminProductController")
@RequestMapping("/admin/product")
@Api(tags = "商品相关接口")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="product";


    /**
     * 添加商品
     * @param productDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加商品")
    public Result save(@RequestBody ProductDTO productDTO){
        log.info("添加的商品信息：{}",productDTO);
        productService.save(productDTO);
        redisTemplate.delete(KEY);
        return Result.success();
    }

    /**
     * 商品分页查询
     * @param productPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("商品分页查询")
    public Result<PageResult> pageQuery(ProductPageQueryDTO productPageQueryDTO){
        log.info("商品分页查询：{}",productPageQueryDTO);

        PageResult pageResult=productService.pageQuery(productPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询商品信息")
    public Result<Product> getById(@PathVariable Integer id){
        Product product=productService.getById(id);
        return Result.success(product);
    }

    /**
     * 修改商品信息
     * @param productDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改商品信息")
    public Result update(@RequestBody ProductDTO productDTO){
        log.info("修改商品信息：{}",productDTO);

        productService.update(productDTO);

        redisTemplate.delete(KEY);

        return Result.success();
    }

    /**
     * 售卖停售商品
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("售卖停售商品")
    public Result startOrStop(@PathVariable Integer status,Integer id){
        log.info("售卖停售商品：{},{}",status,id);
        productService.startOrStop(status,id);
        redisTemplate.delete(KEY);
        return Result.success();
    }

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除商品")
    public Result<String> deleteById(Integer id){
        log.info("删除商品：{}",id);
        productService.deleteById(id);
        redisTemplate.delete(KEY);
        return Result.success();
    }
}

package com.sky.service;

import com.sky.dto.ProductDTO;
import com.sky.dto.ProductPageQueryDTO;
import com.sky.entity.Product;
import com.sky.result.PageResult;

public interface ProductService {
    /**
     * 添加商品
     * @param productDTO
     */
    void save(ProductDTO productDTO);

    /**
     * 商品分页查询
     * @param productPageQueryDTO
     * @return
     */
    PageResult pageQuery(ProductPageQueryDTO productPageQueryDTO);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Product getById(Integer id);

    /**
     * 修改商品信息
     * @param productDTO
     */
    void update(ProductDTO productDTO);

    /**
     * 售卖停售商品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Integer id);

    /**
     * 删除商品
     * @param id
     */
    void deleteById(Integer id);
}

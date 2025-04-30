package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.ProductDTO;
import com.sky.dto.ProductPageQueryDTO;
import com.sky.entity.Product;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.ProductMapper;
import com.sky.result.PageResult;
import com.sky.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加商品信息
     * @param productDTO
     */
    public void save(ProductDTO productDTO) {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);

        productMapper.insert(product);
    }

    /**
     * 商品分页查询
     * @param productPageQueryDTO
     * @return
     */
    public PageResult pageQuery(ProductPageQueryDTO productPageQueryDTO) {
        PageHelper.startPage(productPageQueryDTO.getPage(), productPageQueryDTO.getPageSize());

        Page<Product> page=productMapper.pageQuery(productPageQueryDTO);

        Long total=page.getTotal();

        List<Product> records=page.getResult();

        return new PageResult(total,records);
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    public Product getById(Integer id) {
        Product product = productMapper.getById(id);
        return product;
    }

    /**
     * 修改商品信息
     * @param productDTO
     */
    public void update(ProductDTO productDTO) {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        productMapper.update(product);
    }

    /**
     * 售卖停售商品
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Integer id) {
        Product product=Product.builder()
                .id(id)
                .status(status)
                .build();
        productMapper.update(product);
    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteById(Integer id) {
        Product product=productMapper.getById(id);
        if(product.getStatus()== StatusConstant.ENABLE){
            throw new DeletionNotAllowedException(MessageConstant.PRODUCT_ON_SALE);
        }
        productMapper.deleteById(id);
    }
}

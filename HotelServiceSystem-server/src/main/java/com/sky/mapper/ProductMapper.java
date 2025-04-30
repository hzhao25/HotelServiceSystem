package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.ProductPageQueryDTO;
import com.sky.entity.Product;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    /**
     * 查商品表多少条数据
     * @return
     */
    @Select("select count(*) from product")
    int selectProduct();

    /**
     * 添加商品信息
     * @param product
     */
    @AutoFill(OperationType.INSERT)
    void insert(Product product);

    /**
     * 商品分页查询
     * @param productPageQueryDTO
     * @return
     */
    Page<Product> pageQuery(ProductPageQueryDTO productPageQueryDTO);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product getById(Integer id);

    /**
     * 修改商品信息
     * @param product
     */
    @AutoFill(OperationType.UPDATE)
    void update(Product product);

    /**
     * 删除商品
     * @param id
     */
    @Delete("delete from product where id=#{id}")
    void deleteById(Integer id);
}

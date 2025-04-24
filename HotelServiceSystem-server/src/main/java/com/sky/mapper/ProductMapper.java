package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    /**
     * 查货物表多少条数据
     * @return
     */
    @Select("select count(*) from product")
    int selectProduct();
}

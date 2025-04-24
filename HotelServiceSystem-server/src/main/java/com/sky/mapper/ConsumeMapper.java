package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConsumeMapper {
    /**
     * 查消耗品表多少条数据
     * @return
     */
    @Select("select count(*) from consume")
    int selectConsume();
}

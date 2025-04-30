package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.ConsumePageQueryDTO;
import com.sky.entity.Consume;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 添加消耗品信息
     * @param consume
     */
    @AutoFill(OperationType.INSERT)
    void insert(Consume consume);

    /**
     * 消耗品分页查询
     * @param consumePageQueryDTO
     * @return
     */
    Page<Consume> pageQuery(ConsumePageQueryDTO consumePageQueryDTO);

    /**
     * 根据id查询消耗品信息
     * @param id
     * @return
     */
    @Select("select * from consume where id=#{id}")
    Consume getById(Integer id);

    /**
     * 修改消耗品信息
     * @param consume
     */
    @AutoFill(OperationType.UPDATE)
    void update(Consume consume);

    /**
     * 删除消耗品
     * @param id
     */
    @Delete("delete from consume where id=#{id}")
    void deleteById(Integer id);
}

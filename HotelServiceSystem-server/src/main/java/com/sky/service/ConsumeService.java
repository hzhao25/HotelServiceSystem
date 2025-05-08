package com.sky.service;

import com.sky.dto.ConsumeDTO;
import com.sky.dto.ConsumePageQueryDTO;
import com.sky.entity.Consume;
import com.sky.result.PageResult;

import java.util.List;

public interface ConsumeService {
    /**
     * 添加消耗品
     * @param consumeDTO
     */
    void save(ConsumeDTO consumeDTO);

    /**
     * 消耗品分页查询
     * @param consumePageQueryDTO
     * @return
     */
    PageResult pageQuery(ConsumePageQueryDTO consumePageQueryDTO);

    /**
     * 根据id查询消耗品信息
     * @param id
     * @return
     */
    Consume getById(Integer id);

    /**
     * 修改消耗品信息
     * @param consumeDTO
     */
    void update(ConsumeDTO consumeDTO);

    /**
     * 售卖停售消耗品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Integer id);

    /**
     * 删除消耗品
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询所有消耗品
     * @return
     */
    List<Consume> select();
}

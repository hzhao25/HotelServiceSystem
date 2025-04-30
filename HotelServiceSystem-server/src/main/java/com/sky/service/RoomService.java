package com.sky.service;

import com.sky.dto.RoomDTO;
import com.sky.dto.RoomPageQueryDTO;
import com.sky.entity.Room;
import com.sky.result.PageResult;

public interface RoomService {
    /**
     * 添加客房
     * @param roomDTO
     */
    void save(RoomDTO roomDTO);

    /**
     * 客房分页查询
     * @param roomPageQueryDTO
     * @return
     */
    PageResult pageQuery(RoomPageQueryDTO roomPageQueryDTO);

    /**
     * 根据id查询客房信息
     * @param id
     * @return
     */
    Room getById(Integer id);

    /**
     * 修改客房信息
     * @param roomDTO
     */
    void update(RoomDTO roomDTO);

    /**
     * 售卖停售客房
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Integer id);

    /**
     * 删除客房
     * @param id
     */
    void deleteById(Integer id);
}

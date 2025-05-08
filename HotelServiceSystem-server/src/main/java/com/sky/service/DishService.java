package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据菜品id集合批量删除菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 获取菜品信息及口味
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品信息
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 修改菜品的起售停售状态
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 查询所有的菜品数据
     * @return
     */
    List<DishVO> select();
}

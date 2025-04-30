package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.RoomPageQueryDTO;
import com.sky.entity.Room;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoomMapper {
    /**
     * 查客房表多少条数据
     * @return
     */
    @Select("select count(*) from room")
    int selectRoom();

    /**
     * 添加客房信息
     * @param room
     */
    @AutoFill(OperationType.INSERT)
    void insert(Room room);

    /**
     * 客房分页查询
     * @param roomPageQueryDTO
     * @return
     */
    Page<Room> pageQuery(RoomPageQueryDTO roomPageQueryDTO);

    /**
     * 根据id查询客房信息
     * @param id
     * @return
     */
    @Select("select * from room where id=#{id}")
    Room getById(Integer id);

    /**
     * 修改客房信息
     * @param room
     */
    @AutoFill(OperationType.UPDATE)
    void update(Room room);

    /**
     * 删除客房
     * @param id
     */
    @Delete("delete from room where id=#{id}")
    void deleteById(Integer id);

    /**
     * 清空客房住户信息
     * @param id
     */
    @Update("update room set user_id=null where id=#{id}")
    void updateUserIdNull(Integer id);

    /**
     * 填补客房住户信息
     * @param id
     */
    @Update("update room set user_id=1 where id=#{id}")
    void updateUserIdNotNull(Integer id);
}

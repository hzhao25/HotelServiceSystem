package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入数据
     * @param category
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into service_category(name,type,status,start_time,end_time,created_at, updated_at, create_user, update_user)" +
            " VALUES" +
            " (#{name},#{type},#{status},#{startTime},#{endTime},#{createdAt}, #{updatedAt}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from service_category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Select("select * from service_category where id=#{id}")
    Category selectById(Long id);

    /**
     * 根据id修改分类
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    /*List<Category> list(Integer type);*/
}

package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from staff where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 添加员工信息
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into staff (id,name,phone,password,type,skills,status,created_at,updated_at,sex,username,idNumber,createUser,updateUser) " +
            "values(#{id},#{name},#{phone},#{password},#{type},#{skills},#{status},#{createdAt},#{updatedAt},#{sex},#{username},#{idNumber},#{createUser},#{updateUser})")
    void save(Employee employee);

    /**
     * 分页查询员工信息
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void startOrStop(Employee employee);

    /**
     * 根据id查询员工信息，展示在修改员工信息页面
     * @param id
     * @return
     */
    @Select("select * from staff where id=#{id}")
    Employee getById(Long id);

    /**
     * 更新员工信息
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 根据员工类型查找员工
     * @param type
     */
    @Select("select * from staff where type=#{type}")
    List<Employee> selectByType(String type);
}

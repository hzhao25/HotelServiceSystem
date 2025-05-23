package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void starOrStop(Integer status, long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);

    Employee loginC(EmployeeLoginDTO employeeLoginDTO);

    Employee getByPhone(String phone);

    void updatePassword(String newPassword, Long id);
}

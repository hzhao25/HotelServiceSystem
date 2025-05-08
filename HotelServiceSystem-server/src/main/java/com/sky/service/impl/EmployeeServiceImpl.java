package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        //输入的用户名和密码
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在，抛出设定好的异常
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //只能管理员登录
        if(!employee.getType().equals("管理员")){
            throw new AccountLockedException(MessageConstant.NOT_MANAGER);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误，抛出设定好的异常
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定，抛出设定好的异常
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * C端的员工登录
     * @param employeeLoginDTO
     * @return
     */
    public Employee loginC(EmployeeLoginDTO employeeLoginDTO) {
        //输入的用户名和密码
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在，抛出设定好的异常
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误，抛出设定好的异常
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定，抛出设定好的异常
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工信息
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();

        //对象属性拷贝,因为employeeDTO和employee有相同的属性
        BeanUtils.copyProperties(employeeDTO,employee);

        //设置账号状态
        employee.setStatus(StatusConstant.ENABLE);

        //设置密码，默认密码123456，md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        /*//设置当前记录的创建时间和修改时间
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        //设置当前记录的创建者和更新者
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());*/

        employeeMapper.save(employee);
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //select * from employee limit 0,10(第0行开始查10行数据)
        //从 employeePageQueryDTO 对象中获取当前请求的页码和每页要显示的记录数
        //调用 PageHelper.startPage() 方法：在执行 SQL 查询之前，调用该方法并传入页码和每页记录数，这会在当前线程中保存分页信息（ThreadLocal）
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());

        Page<Employee> page=employeeMapper.pageQuery(employeePageQueryDTO);

        //总记录数
        long total=page.getTotal();
        //当前页的记录数
        List<Employee> records=page.getResult();

        return new PageResult(total,records);
    }

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    public void starOrStop(Integer status, long id) {
        //为了泛用性更强，不仅仅只更新status字段
        Employee employee=new Employee();
        employee.setStatus(status);
        employee.setId(id);

        /*employee.setUpdateUser(BaseContext.getCurrentId());
        employee.setUpdatedAt(LocalDateTime.now());*/

        employeeMapper.startOrStop(employee);
    }

    /**
     * 根据员工id查找员工信息,展示在修改员工信息页面
     * @return
     */
    public Employee getById(Long id) {
        Employee employee=employeeMapper.getById(id);
        //修改员工信息页面没有更改密码的选项，所以不用担心密码被更改
        // //加密显示是为了返回的result的data里看不到密码的详情
        employee.setPassword("*****");
        return employee;
    }

    /**
     * 更新员工信息
     * @param employeeDTO
     */
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

        /*employee.setUpdatedAt(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());*/
        employeeMapper.update(employee);
    }

}

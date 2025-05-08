package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * 技术：
 * md5密码加密解密
 * TreadLocal管理当前线程里的数据信息（员工编号）
 * Swagger生成接口文档，在线调试接口
 * 分页查询PageHelper帮助员工信息分页查找
 * yml配置文件配置驼峰命名映射
 * 创建对象映射器JacksonObjectMapper和springMVC消息转换器，达成统一日期格式
 * 注解和AOP切片操作将员工和分类的统一字段进行处理，减少代码的冗余
 * 阿里云Oss远程上传文件，利用UUID重命名文件名，配置基本信息propertise，工具类Utils，配置工具类的配置文件configuration
 * 在小程序可能同时有大量用户查询商品/消耗品/菜品的信息，如果每次查询都要访问数据库，性能会下降
 * 所以把这些数据放到redis缓存当中，加快查询速度，当后端增删改查这些数据时，redis缓存要重新加载
 */

/**
 * 员工管理
 */
@RestController("adminEmployeeController")
@RequestMapping("/admin/employee")
@Slf4j//日志
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),//签名（密钥）
                jwtProperties.getAdminTtl(),//时间
                claims);//自定义内容

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        /*生成token后将其设置到EmployeeLoginVO对象中返回给客户端，客户端在后续的请求中会携带这个token。
        当服务器接收到带有token的请求时，就可以通过JwtUtil.parseJWT方法来解析token，获取其中的用户信息，从而实现对用户身份的验证和授权等操作*/
        return Result.success(employeeLoginVO);//json格式的数据返回客户端
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 添加员工
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增员工")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工:{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询:{}",employeePageQueryDTO);

        PageResult pageResult=employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     * @return
     */
    //一般用于向服务器提交数据，比如创建新的资源
    @PostMapping("/status/{status}")
    @ApiOperation("启动禁用员工账号")
    public Result startOrStop(@PathVariable Integer status, long id){
        log.info("启用禁用员工账号:{},{}",status,id);
        employeeService.starOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据id查找员工信息，展示在修改员工信息页面
     * @param id
     * @return
     */
    //从服务器获取资源。它不会对服务器上的资源产生修改
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee=employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     **更新员工信息
     * @param employeeDTO
     * @return
     */
    //主要用于更新服务器上的资源。如果资源不存在，有些情况下也可以用于创建资源
    @PutMapping
    @ApiOperation("更新员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工信息:{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

}

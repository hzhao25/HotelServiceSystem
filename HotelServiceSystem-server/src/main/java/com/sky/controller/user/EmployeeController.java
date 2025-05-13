package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.ForgetPasswordDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.mapper.EmployeeMapper;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.service.OrderService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.interfaces.PBEKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController("userEmployeeController")
@Api(tags = "C端员工登录接口")
@Slf4j
@RequestMapping("/user/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtProperties jwtProperties;

    public static final int VERIFYLENGTH=4;

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @PostMapping("/login")
    @ApiOperation("C端员工登录")
    public Result login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("小程序端的员工登录：{}",employeeLoginDTO);

        Employee employee=employeeService.loginC(employeeLoginDTO);

        Map<String,Object> claims=new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,employee.getId());
        String token= JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims
        );

        EmployeeLoginVO employeeLoginVO= EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 生成C端员工忘记密码的验证码
     * @return
     */
    @GetMapping("/code")
    @ApiOperation("C端员工验证码")
    public Result<String> forget(){
        StringBuffer code =new StringBuffer();
        Random random=new Random();

        for(int i=0;i<VERIFYLENGTH;i++){
            int index=random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return Result.success(code.toString());
    }

    /**
     * 查询员工订单
     * @return
     */
    @GetMapping("/order")
    @ApiOperation("查询员工的订单")
    public Result<List<OrderVO>> selectOrderByStaffId(Long staffId){
        List<OrderVO> list= orderService.selectOrderByStaffId(staffId);

        return Result.success(list);
    }

    /**
     * C端员工忘记密码
     * @param forgetPasswordDTO
     * @return
     */
    @PutMapping("/forget")
    @ApiOperation("C端忘记密码")
    public Result forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO){
        Employee employee = employeeService.getByPhone(forgetPasswordDTO.getPhone());
        log.info("忘记密码的员工:{}", employee.getId());
        String oldPassword= DigestUtils.md5DigestAsHex(forgetPasswordDTO.getOldPassword().getBytes());
        if(oldPassword.equals(employee.getPassword())){
            String newPassword=DigestUtils.md5DigestAsHex(forgetPasswordDTO.getNewPassword().getBytes());
            employeeService.updatePassword(newPassword,employee.getId());
        }
        return Result.success();
    }
}

package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理sql错误
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //报错信息：Duplicate entry 'zs' for key 'staff.staff_username_uindex'
        String message=ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split=message.split(" ");
            String username=split[2];
            String msg=username+MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else{
            String msg=MessageConstant.UNKNOWN_ERROR;
            return Result.error(msg);
        }
    }

}

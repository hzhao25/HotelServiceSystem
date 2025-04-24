package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.utils.Join;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Component;

import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

//表明这是一个切面类，Spring AOP 会识别并处理这个类中的切面逻辑
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点:指定了切面逻辑要应用的目标方法
     */
    //匹配 com.sky.mapper 包下的所有类的所有方法,这些方法必须被 @AutoFill 注解标记
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 前置通知(目标方法执行之前执行该通知中的逻辑)，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinpoint){
        log.info("开始进行公共字段自动填充");

        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature=(MethodSignature) joinpoint.getSignature();//方法签名对象
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType=autoFill.value();//获得数据库操作类型

        //获取到当前被拦截的方法的参数（实体对象）
        Object[] args=joinpoint.getArgs();//获得方法参数
        if(args==null || args.length==0){
            return;
        }
        Object entity=args[0];

        //准备赋值的数据
        LocalDateTime now=LocalDateTime.now();
        Long currentId= BaseContext.getCurrentId();

        //根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(operationType==OperationType.INSERT){
            try {
                //获取实体对象的四个方法，为4个公共字段赋值
                Method setCreatedAt=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATED_AT,LocalDateTime.class);
                Method setUpdatedAt=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATED_AT,LocalDateTime.class);
                Method setUpdateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);
                Method setCreateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);

                //通过反射为对象属性赋值
                setCreatedAt.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdatedAt.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType==OperationType.UPDATE){
            try {
                //获取实体对象的两个方法，为2个公共字段赋值
                Method setUpdatedAt=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATED_AT,LocalDateTime.class);
                Method setUpdateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);

                //通过反射为对象属性赋值
                setUpdatedAt.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

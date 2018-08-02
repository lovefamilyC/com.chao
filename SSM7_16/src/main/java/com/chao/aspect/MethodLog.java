package com.chao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 连接点：
 * 切点：
 * 增强：@Before  @After()  @AfterReturning()  @AfterThrowing()  @Around()
 *
 *
 * 切面：
 */
@Component
//@Aspect
public class MethodLog {
    @Before("within(com.chao.admin.controller.*)")
    public  void  beforeMethod(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        System.out.println("类"+className+"方法"+methodName+"将要执行");

    }

    @After("within(com.chao.*.*.*)")
    public void afterMethod(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        System.out.println("类"+className+"方法"+methodName+"执行完成");

    }

    @AfterThrowing(pointcut ="within(com.chao.*.*.*)",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("类"+className+"方法"+methodName+"出现异常");
        System.out.println("目标方法中抛出的异常:"+e.toString());
    }

    @AfterReturning("within(com.chao.*.*.*)")
    public void afterReturning(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("@afterReturning 方法执行测试：" +className+ methodName+"正常执行结束");
    }






}

package com.chao.log4j;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Component
//@Aspect
public class Log4jDemo {
    @Before("within(com.chao.admin.controller.*||com.chao.account.controller.* " +
            "||com.chao.servicepage.controller.*||com.chao.fee.controller.*||com.chao.login.controller.*)")
    public  void  beforeMethod(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        System.out.println();
        //初始化logger
        Logger log = Logger.getLogger(Log4jDemo.class);
        //加载默认的配置信息
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("这是debug log+==类+"+className+"方法"+methodName+"将要执行");
        log.info("这是info log");
    }




    public static void main(String[] args) {
        //初始化logger
        Logger log = Logger.getLogger(Log4jDemo.class);
        //加载默认的配置信息
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("这是debug log");
        log.info("这是info log");



    }
}


package com.chao.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */

public class MyInvocationHandler implements InvocationHandler {
    Object realProxy;
    Object myProxy;

    public Object getMyProxy(Object realProxy){
        this.realProxy = realProxy;
        myProxy = Proxy.newProxyInstance(realProxy.getClass().getClassLoader(),
                realProxy.getClass().getInterfaces(),this);
        return myProxy;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是代理service");
        Object object = method.invoke(realProxy,args);
        return object;
    }
}

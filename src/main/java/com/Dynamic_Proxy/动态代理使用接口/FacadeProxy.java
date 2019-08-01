package com.Dynamic_Proxy.动态代理使用接口;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 */
public class FacadeProxy implements InvocationHandler {
//    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("接口调用开始");
        System.out.println("toGenericString:"+method.toGenericString());
        System.out.println("方法名:"+method.getName());
        System.out.println("方法参数:"+args[0]);
        System.out.println("接口调用结束");
        return "调用返回值";
    }

    public static  <T> T newMapperProxy(Class<T> mpperInterface){
        ClassLoader loader=mpperInterface.getClassLoader();
        Class<?>[] interfaces=new Class[]{mpperInterface};
        FacadeProxy proxy=new FacadeProxy();
        return (T) Proxy.newProxyInstance(loader,interfaces,proxy);

    }
}

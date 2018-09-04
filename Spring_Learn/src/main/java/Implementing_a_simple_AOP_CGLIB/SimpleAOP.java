package Implementing_a_simple_AOP_CGLIB;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SimpleAOP  implements MethodInterceptor {


    /***
     *
     * @param o 由CGLib动态生成的代理类实例
     * @param method 为实体类所调用的被代理的方法引用
     * @param objects 为参数值列表
     * @param methodProxy 为生成的代理类对方法的代理引用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("之前");
        //调用代理类实例上的proxy方法的父类方法
        Object o1=methodProxy.invokeSuper(o,objects);
        System.out.println("之后");
        return o1;
    }
}

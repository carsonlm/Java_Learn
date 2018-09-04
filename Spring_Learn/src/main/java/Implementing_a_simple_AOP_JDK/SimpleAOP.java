package Implementing_a_simple_AOP_JDK;

import java.lang.reflect.Proxy;

/**
 * 代理类
 * 通过Proxy类的newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)方法来创建代理对象
 * 通过它创建的代理对象时在JVM运行时动态生成的，它并不是InvocationHandler类型，也不是第二个接口参数的类型，它的命名方式一般以&Proxy开始
 *
 * 第一个参数：表示用SimpleAOP这个类的ClassLoader对象来加载代理对象
 * 第二个参数：表示需要代理的真实对象，这样就能掉用这组接口中的方法  提供了什么接口，代理对象就会实现这组接口
 * 第三个参数：表示关联到Advice这个InvocationHandler上
 *
 */
public class SimpleAOP {

    public static Object getProxy(Object bean,Advice advice){

        return Proxy.newProxyInstance(
                SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                advice);
    }
}

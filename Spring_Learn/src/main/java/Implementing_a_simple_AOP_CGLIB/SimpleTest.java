package Implementing_a_simple_AOP_CGLIB;

import net.sf.cglib.proxy.Enhancer;

public class SimpleTest {


    public static void main(String[] args) {

        //Enhancer类是CGLib中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展，
        Enhancer enhancer=new Enhancer();

        // 首先将被代理类HelloServiceImpl设置成父类，
        enhancer.setSuperclass(HelloServiceImpl.class);

        // 设置一个拦截器
        enhancer.setCallback(new SimpleAOP());

        // 动态生成一个代理类
        HelloServiceImpl helloService=(HelloServiceImpl)enhancer.create();

        helloService.sayHelloAOP();
    }

}

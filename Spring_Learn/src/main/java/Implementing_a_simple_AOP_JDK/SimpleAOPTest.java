package Implementing_a_simple_AOP_JDK;

import org.junit.jupiter.api.Test;

public class SimpleAOPTest {

    @Test
    public void getProxy() throws Exception{

        //创建一个MethodInvocation实现类，并初始化
        MethodInvocation logTask = () -> System.out.println("log task start");

        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        //创建一个Advice 即目标对象
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl,logTask);

        //为目标对象生成代理————————这步关联到InvocationHandler的invoke方法
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);

        //？_？ 多加下面这两句带代理对象的输出，调用的handler对象<即BeforeAdvice>的invoke方法会多执行两遍，
        // 可能是由于触发到代理对象就会自动执行handler的invoke方法
        System.out.println("=="+helloServiceImplProxy);
        System.out.println("=="+helloServiceImplProxy.toString());

        /**
         *  当通过代理对象来调用方法的时候，实际是委托由其关联到的handler对象<即BeforeAdvice>的invoke方法中来调用，
         *  并不是自己来真实调用，而是通过代理的方式来调用的
         */
        helloServiceImplProxy.sayHelloAOP();
    }
}
/**
 * 从API的设计和实现的角度，这种实现仍有局限，因为他是以接口为中心的，相当于添加了一种对于被调用者没有太大意义的限制。
 * 我们实例化的是Proxy对象，而不是真正被调用的类型。这在实践中可能带来各种不便和能力退化。
 * 如果被调用者没有实现接口，而我们还是希望利用动态代理机制，那么可以考虑其他方式。
 * 我们知道 Spring AOP 支持两种模式的动态代理，JDK Proxy 或者 cglib，如果我们选择 cglib 方式，你会发现对接口的依赖被克服了
 */
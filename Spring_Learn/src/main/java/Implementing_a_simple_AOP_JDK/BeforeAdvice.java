package Implementing_a_simple_AOP_JDK;

import java.lang.reflect.Method;

/**
 * 前置通知
// * 即 一个动态代理类
 */
public class BeforeAdvice implements Advice {

    /**下面Field为需要代理的真实对象*/
    private Object bean;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean,MethodInvocation methodInvocation){
        this.bean=bean;
        this.methodInvocation=methodInvocation;
    }

    /**
     * 当通过代理对象来调用方法的时候，实际就是委托由其关联到的handler对象<BeforeAdvice>的invoke方法中来调用，并不是自己来真实调用，而是通过代理的方式来调用的
     * 这就是JDK的动态代理
     *
     * @param proxy 指代我们所代理的那个真实对象
     *              这个参数的用途：1，可以用来反射获取代理对象的信息（proxy.getClass().getName()），2，可以将代理对象返回以进行连续调用，这就是proxy的真正用途。
     * @param method 指所要调用真实对象的某个方法的Method对象
     * @param args  指的是调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在目标方法执行前调用通知-即在代理真实对象前，添加自己的操作
        methodInvocation.invoke();

        //输出eg: com.sun.proxy.$Proxy8
        System.out.println(proxy.getClass().getName());

        // 输出eg: sayHelloAOP
        System.out.println(method.getName());

        // 输出 null
        System.out.println(args);





        // 当代理对象调用真实对象的方法时，会自动跳转到代理对象关联的handler对象<BeforeAdvice>的invoke方法来进行调用
        return method.invoke(bean , args);
    }
}

package Implementing_a_simple_AOP_JDK;

/**
 * 目标对象
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHelloAOP() {
        System.out.println("Hello AOP");
    }
}

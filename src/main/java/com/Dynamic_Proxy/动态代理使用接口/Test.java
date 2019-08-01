package com.Dynamic_Proxy.动态代理使用接口;

public class Test {

    public static void main(String[] args) {
        Hi hi=FacadeProxy.newMapperProxy(Hi.class);
        System.out.println(hi.sayHi("你好"));
    }
}

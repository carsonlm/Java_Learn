[cglib开源地址](https://github.com/cglib/cglib)
[ClassLoader详解](https://blog.csdn.net/briblue/article/details/54973413)
[ClassLoader详解2](https://www.cnblogs.com/doit8791/p/5820037.html)

ASM框架是一个Java字节码框架，被用来动态生成类或增强既有类的功能，应用场景主要是AOP，如Spring的AOP等。ASM可以直接生产生二进制class文件，
    也可以在类被加载如JVM虚拟机之前，动态修改类的行为。
    主要核心类：
       ClassReader：用来解析编译过的class字节码文件
       ClassWriter：用来重新构建编译后的类，比如说修改类名、属性以及方法，甚至可以生产新的类的字节码文件。
       ClassAdapter：实现了ClassVisitor接口，它将对它的方法调用委托给另一个对象ClassVisitor对象。
       
CGLIB:是一个高性能的代码生成库。CGLIB库使用ASM（一种小而快速的字节码操作框架）来转换现有的字节码并生成新的类。
      它通过动态生成一个子类来覆盖代理类的非final方法，并连接回调用户定义的拦截器的钩子，来实现和JDKProxy一样的效果，但性能更好。
      除了CGLIB库之外，脚本语言（如Groovy和BeanShell）也使用ASM生成Java字节码
      
      CGLIB重要包如下：
         net.sf.cglib.beans
             与JavaBean相关的实用程序
         net.sf.cglib.core
             字节码操作类; 他们中的大多数都与ASM有关。
         net.sf.cglib.proxy
              用于代理创建和方法拦截的类
         net.sf.cglib.reflect
              用于更快反射和C＃样式委托的类
          net.sf.cglib.transform
             运行时或构建时的类文件转换的类
         net.sf.cglib.transform.impl
         net.sf.cglib.util
             收集排序实用程序
             
[参考1](http://www.iteye.com/topic/799827)
[参考2](http://jnb.ociweb.com/jnb/jnbNov2005.html)


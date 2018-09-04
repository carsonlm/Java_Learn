[参考](http://www.360doc.com/content/14/0801/14/1073512_398598312.shtml)
### 动态代理机制
* 它首先是一个代理机制，如果熟悉设计模式中的代理模式，就知道代理可以看做是对调用目标的一个包装，这样对目标对象的调用就是通过代理完成的，实现了解耦。
* 实现动态代理的机制有很多，比如JDK自身提供的动态代理（利用java语言的反射机制），其他方式还有利用更高性能的字节码操作机制，
   类似于ASM，cglib<基于ASM>,Javassist等
* 比如用来包装RPC 调用，面向切面编程AOP
* 在jdk动态代理机制中，最重要的类或接口就是Proxy和InvocationHandler
  * Proxy: 这个类的作用就是用来动态创建一个代理对象的类，他最常用的方法就newProxyInstance
     * public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h)
          * loader: 一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
          * interfaces：一个interfaces对象数组，表示的是我将要给需要代理的对象提供一组什么接口，如果提供了一组接口那么这个代理对象就实现了该接口（多态），这样就能调用这组接口中的方法
          * h: 一个InvocationHandler对象，表示当动态代理对象调用方法的时候，会关联到哪个InvocationHandler上
  * InvocationHandler 
     * public Object invoke(Object proxy, Method method, Object[] args)
          * proxy 指所代理的那个真实对象;这个参数的用途：1，可以用来反射获取代理对象的信息（proxy.getClass().getName()），2，可以将代理对象返回以进行连续调用，这就是proxy的真正用途。
          * method 指所要调用真实对象的某个方法的Method对象
          * args 指的是调用真实对象某个方法时接受的参数
  
  
  
**Spring AOP 使用不同的技术在运行时创建代理：JDK Proxy和CGLIB**
* 如果目标类实现了一个或多个接口，那么spring将创建一个实现了每个接口的JDK动态代理。如果目标类没有实现接口，Spring将使用CGLIB动态创建一个新类，它是目标的子类("extends").
    * 这导致了一个重要的区别：JDK代理无法转为原始目标类，因为它只是一个动态代理，恰好实现了与目标相同的接口。
    而CGLIB可以像目标类本身一样。
* **JDK Proxy的优势:**  
   * 最小化依赖关系，
   * 平滑进行JDK版本升级，而字节码类库通常需要更新以保证新版本的java使用
   * 代码实现简单  
* **基于CGLIB框架的优势：**  
   * 有的时候调用目标可能不便实现额外接口，从而在运行期扩展Java类与实现Java接口；从某种角度看，限定调用者实现接口是有些侵入性的实践，类似 cglib 动态代理就没有这种限制。
   * 只操作我们关心的类，而不必管其他相关类
   * 高性能
   *
   ***CGLIB创建某个类A的动态代理类的模式是：***  
           1. 查找类A上所有非final的public类型的方法  
           2. 将这些方法的定义转换成字节码  
           3. 将组成的字节码转换成相应代理的class对象  
           4. 实现MethodInterceptor接口。用来处理对代理上所有方法的请求(这个接口的JDK动态代理InvocationHandler的功能和角色是一样的)
           
           
    

### AOP应用场景
***AOP用来封装横切关注点***
* Authentication 权限
* Cashing 缓存
* Error handing 错误处理
* Lazy loading 懒加载
* Debugging 调试
* logging  tracing profiling monitoring 记录跟踪 优化 校准
* Performance optimization 性能优化
* persistence 持久化
* Resource pooling 资源池
* Synchronized 同步
* Transactions 事务
* 。。。 

### AOP相关概念
#### 通知 Advice
 * 前置通知Before：在目标方法执行前，执行通知
 * 后置通知After：在目标方法执行后执行通知，此时不关心目标方法返回的结果是什么
 * 返回通知After-returning：在目标方法执行后，执行通知
 * 异常通知After-throwing：在目标方法抛出异常后执行
 * 环绕通知Around：目标方法被通知包裹，通知在目标方法执行前后执行后都会被调用
 
 #### 切点PointCut
 
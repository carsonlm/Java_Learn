使用需要 Lombok 插件 & Lombok jar包
#### lombok 相关注解及作用
1. val 相当于定义一个final变量
    * var hash=new HashMap(); == final Hash hash=new HashMap();
2. @NotNull 用于构造函数或者方法的参数进行非空校验
3. @Cleanup 注解能自动释放资源
    *  ```
    
          try{
              @Cleanup Jedis jedis=redisService.getJedis();
          }catch(Exception e){
             ...
          }
       //上面的代码相当于
        Jedis jedis=null;
         try{
            jedis=redisService.getJedis();       
          }catch(Exception e){
           ...
          }finally{
             if(jedis!=null){
                try{
                   jedis.close();
                }catch(Exception e){
                  ...
                }
             }
          }
        ```
4. @Getter & @Setter 用在类字段上自动生成getter、setter
5. @ToString 为使用该注解的类生成一个toString方法，默认的toString格式为：ClassName(fieldName= fieleValue ,fieldName1=fieleValue)。
6. @EqualAndHashCode 为使用该注解的类自动生成equals和hashCode方法。
7. @NoArgsConstructor,@RequiredArgsConstructor,@AllArgsConstructor分别为类自动生成了无参构造器、指定参数的构造器和包含所有参数的构造器
8. @Data 作用比较全，比较常用，其包含注解的集合@ToString，@EqualsAndHashCode，所有字段的@Getter和所有非final字段的@Setter, @RequiredArgsConstructor。
9. @Builder 提供了一种构建值对象的方式
10. @Synchronized 提供了类似synchronized关键字  但可以隐藏同步锁
     ```java
     
         public class SynchronizedExample { 
         
          private final Object readLock = new   Object(); 
         
          @Synchronized 
          public static void hello() { 
              System.out.println("world");   
          } 
         
          @Synchronized("readLock") 
          public void foo() { 
            System.out.println("bar"); 
          } 
         
         //上面代码相当于如下：
         
          public class SynchronizedExample { 
         
           private static final Object $LOCK = new   Object[0]; 
           private final Object readLock = new   Object(); 
         
           public static void hello() { 
             synchronized($LOCK) { 
               System.out.println("world"); 
             } 
           }   
           public void foo() { 
             synchronized(readLock) { 
                 System.out.println("bar");   
             } 
           } 
         }
     ```
### Lombok 的自定义原理：
  * Lombok 这款插件依靠可插件化的 java自定义注解处理API<JSR 269:Pluggable Annotation  processing API>来实现在javac编译阶段利用
  'Annotation Processing' 对自定义的注解进行预处理后生成真正在JVM上执行的class 文件。可反编译带有lombok的类探探究竟
  * 执行原理图
   ![执行原理图](http://blog.didispace.com/content/images/posts/java-lombok-how-to-use-4.png)
  * 从上可看出Annotation Processing是编译器在解析java源码生成class文件之间进行的。lombok插件具体执行流程如下：
  ![](http://blog.didispace.com/content/images/posts/java-lombok-how-to-use-5.png)
  
  [关于Annotation Processing 可参考](https://www.cnblogs.com/throwable/p/9139908.html) 
1. 什么是SpringBoot Starter
    * Starter主要是用来简化依赖。
    
2. 常见的starter会包含几方面的内容？分别是什么？
    * 通常会包含下面四方面的内容： 
       1. 自动配置文件，根据classpath是否存在指定的类来决定是否执行该功能的自动配置。
       2. spring.factories 非常重要， 指导Spring Boot找到指定的自动配置文件
       3. endpoint 可以理解为一个admin，包含对服务的描述、界面、交互(业务信息的查询)
       4. health indicator :该starter提供的服务的健康指标
    * 注意：
       1. @ConditionalMissingBean的作用：只有对应的bean在系统中没有被创建，它修饰的代码块才会执行【用户自己创建的bean优先】
       2. spring boot starter找到自动配置文件(xxxxAutoConfiguration之类的文件)的两种方式：
          1. spring.factories:由spring boot触发探测classpath目录下的类，进行自动配置
          2. @Enablexxx:有时需要starter的用户触发*查找自动配置的过程

3. spring boot starter的工作原理
    * spring 在启动时扫面项目所依赖的jar包，寻找包含spring.factories文件的jar
    * 根据spring.factories配置加载AutoConfigure类
    * 根据@Conditional注解的条件，进行自动配置，并将 bean注入Spring Context
    
4.  谈谈你对spring boot的认识
```
   spring Boot是一个开源框架，采用了习惯优于配置的方法。此框架的神奇之处在于@EnableAutoConfiguration注解，此注释自动载入应用程序所需的所有Bean——这依赖于Spring Boot在类路径中的查找。
   1. @Enable*注释
    
   @Enable*注释并不是新发明的注释，早在Spring 3框架就引入了这些注释，用这些注释替代XML配置文件。 
   很多Spring开发者都知道@EnableTransactionManagement注释，它能够声明事务管理；@EnableWebMvc注释，它能启用Spring MVC；以及@EnableScheduling注释，它可以初始化一个调度器。 
    
   2. 属性映射
    
   下面看MongoProperties类，它是一个Spring Boot属性映射的例子：
    
   @ConfigurationProperties(prefix = "spring.data.mongodb")
   public class MongoProperties {
    
       private String host;
       private int port = DBPort.PORT;
       private String uri = "mongodb://localhost/test";
       private String database;
    
       // ... getters/ setters omitted
   }
   @ConfigurationProperties注释将POJO关联到指定前缀的每一个属性。例如，spring.data.mongodb.port属性将映射到这个类的端口属性。 
   强烈建议Spring Boot开发者使用这种方式来删除与配置属性相关的瓶颈代码。
    
   3.@Conditional注释
    
   Spring Boot的强大之处在于使用了Spring 4框架的新特性：@Conditional注释，此注释使得只有在特定条件满足时才启用一些配置。 
   在Spring Boot的org.springframework.boot.autoconfigure.condition包中说明了使用@Conditional注释能给我们带来什么，下面对这些注释做一个概述：
    
   @ConditionalOnBean
   @ConditionalOnClass
   @ConditionalOnExpression
   @ConditionalOnMissingBean
   @ConditionalOnMissingClass
   @ConditionalOnNotWebApplication
   @ConditionalOnResource
   @ConditionalOnWebApplication
    
   4.应用程序上下文初始化器
    
   spring.factories还提供了第二种可能性，即定义应用程序的初始化。这使得我们可以在应用程序载入前操纵Spring的应用程序上下文ApplicationContext。 
   特别是，可以在上下文创建监听器，使用ConfigurableApplicationContext类的addApplicationListener()方法。 
   AutoConfigurationReportLoggingInitializer监听到系统事件时，比如上下文刷新或应用程序启动故障之类的事件，Spring Boot可以执行一些工作。这有助于我们以调试模式启动应用程序时创建自动配置的报告。 
   要以调试模式启动应用程序，可以使用-Ddebug标识，或者在application.properties文件这添加属性debug= true。
```

5. 自定义spring-boot-starter的注意事项
   1. spring-boot 默认scan的包名是其main类所在的包名。如果引入的starter包名不一样，需要自己添加scan
       * @ComponentScan(basePackages={"com.xixicat.demo","com.xixicat.sms"})
   2. 对starter中有feign的，需要额外指定
       * @EnableFeignClients(basePackages={"com.xixicat.sms"}
   3. 对exclude的一些autoConfig
       * @EnableAutoConfiguration(exclude={MetricFilterAutoConfiguration.class})

6. spring boot三大特性
    1. 组件自动装配： web MVC 、 web flux 、jdbc等
    2. 嵌入式web容器：Tomcat、jetty以及undertow
    3. 生成准备特性： 指标、健康检查、外部化配置

    

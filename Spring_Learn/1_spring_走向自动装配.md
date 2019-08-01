1. spring framework 手动装配
   * spring 模式注解装配：一种用于声明在应用中扮演“组件”角色的注解
        * 组件举例：@Component、@Service、@Configuration
        * 装配方式：\<context:component-scan> 或@ComponentScan 
   * spring @Enable 模块装配：具备相同领域的功能组件集合，组合所形成一个独立的单元
        * eg @EnableWebMvc、@EnableAutoConfiguration
        * 实现方式： 注解实现、编程实现
   * spring 条件装配 从spring framework 3.1开始 允许在bean装配时增加前置条件判断
        * eg @Profile<配置化条件装配> 3.1  @Conditional<编程条件装配> 4.0
        * 实现方式： 注解方式 、编程方式

2. Spring boot自动装配 ：基于约定大于配置的原则，实现spring组件自动装配的目的
   * 底层装配技术：模式注解、@Enable模块、条件装配、工厂加载机制<实现类：SpringFactoriesLoader 3.2 配置资源：META-INF/spring.factories>
   * 实现步骤： 激活自动装配-@EnableAutoConfiguration、实现自动装配-XXXAutoConfiguration、配置自动装配实现-META-INF/spring.factories
   * 举例： META-INF/spring.factories
   
   * 自定义自动装配：
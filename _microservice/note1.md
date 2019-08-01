微服务是一种架构风格，、
应该具备的特性
```
        -每个微服务可以独立运行在自己的进程里
        -每个服务为独立的业务开发，一个微服务只关注一个特定功能
        -微服务之间通过轻量的通信机制进行同行
        -可以使用不同的语言与数据存储技术
        -全自动的部署机制
      面临的挑战：
          -运维要求高
          -分布式固有的复杂性（系统容错，网络延迟，分布式事务）
          -接口调整成本高（使用接口进行通信）
          -重复劳动：没有达到微服务程度的功能代码可能各自都要开发一遍（可以使用共享库，但是在多语言的环境下行不通）
```

开源微服务框架包括Spring Cloud、Dubbo、gRPC，Service Mesh（用户对保证服务间通信的端到端性能和可靠性的需求）

微服务架构的理论基础——康威定律
```
     Organizations which design systems are constrained to produce designs which are copies of the communication structures of these organizations. - Melvin Conway(1967)
        简言：组织形式等同于系统设计
        Design RESTful API的作者从他的角度归纳了他的论文中的一些核心观点
      第一定律：Communication dictates design 组织沟通方式会通过系统设计表达出来
      第二定律：There is never enough time to do something right, but there is always enough time to do it over
               时间再多一件事情也不可能做的完美，但是总有时间做完一件事情
      第三定律：There is a homomorphism from the linear graph of a system to the linear graph of its design organization
                线型系统和线型组织架构间有潜在的异质同态特性
      第四定律：The structures of large systems tend to disintegrate during development, qualitatively more so than with small systems
                大的系统组织总是比小系统更倾向于分解

      康威法则给我们的启示：软件系统的接口结构会映射组织的沟通结构，如果组织架构不合理，就无法建立一个高效的系统架构。一般在系统架构调整时，要提前考虑相应的组织架构的调整，两边联动才能产生效果。
```

单体应用架构<容易测试，容易部署。开发效率低，代码维护难 ，部署不灵活，稳定性不高，扩展性不够>，
垂直应用架构，
分布式服务架<旨在支持应用程序和服务开发，可以利用物理架构由多个自治的处理元素
      ，不共享主内存，但通过网络发送消息合作>，流动计算架构

微服务架构的基础框架/组件
服务注册发现：-------------------------------------对应spring cloud Eureka
服务网关（service Gateway）
后端通用服务（也称中间层服务Middle Tier Service）
前端服务（也称边缘服务Edge Service）

spring cloud 是一个开发工具集，含了多个子项目
     -利用spring boot的开发便利
     -主要是基于对Netflix开源组件的进一步封装
     
作用：spring cloud简化了分布式开发  
   -如何使用，理解分布式，架构的特点

微服务的设计原则：
    -单一职责原则
    -服务自治原则  
          每个微服务应该具备独立的业务能力，依赖，与运行环境。服务是独立的业务单元，应该与其他服务高度解耦
    -轻量级通信机制
         （这个机制应该体量轻，跨语言，跨平台）REST，AMQP，STOMP，MQTT
    -微服务粒度     
          应该使用合理的粒度划分微服务，不是一味的把微服务做小。 

服务注册与发现：
   应该具备的功能：
      1，服务注册表：用来记录各个微服务的信息，如名称，IP,端口等。提供查询API<用于查询可用的微服务实例>和管理API<用于服务的注册和注销>。
      2，服务的注册与服务发现：服务注册是指微服务在启动时，将自己的信息注册到服务发现的组件上的过程。服务发现是指查询可用微服务列表及网络地址的机制。
      3，服务检查：服务发现组件使用一定定时检测已注册的服务，如发现某实例时间无法访问，就会从服务注册表中移除该实例。

      服务发现的方式可以细分为服务端发现和客户端发现。
  
  spring cloud提供多种服务发现组件的支持，如Eureka，Consul和Zookeeper等

       Eureka
      --本身是一个基于REST的服务
      --基于Netflix Eureka做了二次封装
      --组成：
            --Eureka Service 注册中心
                  提供服务发现的能力，各个微服务启动时，会向Eureka Server注册自己的信息
            --Eureka Client 服务注册    维持心跳连接
               简化与Eureka server的交互
            轮询负载均衡器 提供服务的故障切换支持

            -微服务启动后会周期性（默认30s）地向Eureka Server发送心跳以续约自己的“租期”。
            -如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（90s）。
            -默认情况下，Eureka Server同时也是Eureka Client。多个Eureka Server实例，相互之间通过复制的方式，来实现注册表中的数据同步。
            -Eureka Client会缓存服务注册表中的信息，这种方式有一定的优势--首先微服务无序每次都查询Eureka Server，从而降低Eureka Server的压力；
              其次,即使Eureka Server所有节点都宕掉，服务消费者依然可以从缓存中的信息找到服务提供者并完成调用。
            综上，Eureka通过心跳检查，客户端缓存等机制，提供了系统的灵活性，可伸缩性和可用性。


      Eureka原理：
         先了解Region和Availability Zone  --均是AWS的概念
         Reginon表示AWS的中的地理位置，每个Region都有多个Availability Zone，各个Region之间完全隔离。AWS通过这种方式实现了最大的容错和稳定性。
         spring cloud默认使用的Region是us-east-1，在非AWS环境下，可以将Availability Zone理解为机房，将Region理解为跨机房的Eureka集群。

      在生产环境中，通常会部署一个高可用的Eureka Service集群。
         Eureka Service可以通过运行多个实例并相互的方式实现高可用部署。Eureka Server实例会彼此增量地同步信息，从而确保所有节点数据一致。
         事实上，节点之间相互注册是Eureka Service的默认行为。


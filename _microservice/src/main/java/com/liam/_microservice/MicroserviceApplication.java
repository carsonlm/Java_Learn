package com.liam._microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**注解启动一个服务注册中心提供给其他应用进行对话，在默认情况下，该服务注册中心也会将自己作为客户端来注册自己，所以需要在application.properties中禁用他的客户端注册行为*/
@EnableEurekaServer
@SpringBootApplication
public class MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}
}

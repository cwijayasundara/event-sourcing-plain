package com.cham.feignclientapp;

import com.cham.eventsourcecomponent.stream.AuditStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = "com.cham")
@EnableBinding(AuditStream.class)
public class FeignClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientAppApplication.class, args);
	}
}

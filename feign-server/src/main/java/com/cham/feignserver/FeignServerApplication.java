package com.cham.feignserver;

import com.cham.eventsourcecomponent.stream.AuditStream;
import com.cham.feignserver.stream.TradeStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding({TradeStream.class, AuditStream.class})
@ComponentScan(basePackages = "com.cham")
public class FeignServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignServerApplication.class, args);
	}

}

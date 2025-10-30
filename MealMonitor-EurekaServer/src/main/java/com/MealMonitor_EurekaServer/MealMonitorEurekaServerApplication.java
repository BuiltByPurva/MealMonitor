package com.MealMonitor_EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MealMonitorEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealMonitorEurekaServerApplication.class, args);
	}

}

package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author santo
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class DailyActivtyApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DailyActivtyApplication.class, args);
	}

}

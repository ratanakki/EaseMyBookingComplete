package com.easemybooking;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomeregistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomeregistrationApplication.class, args);
	}
	
	


}

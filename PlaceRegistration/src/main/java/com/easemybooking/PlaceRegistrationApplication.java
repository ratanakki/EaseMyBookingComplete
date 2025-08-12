package com.easemybooking;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.easemybooking.filter.AuthorizationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class PlaceRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceRegistrationApplication.class, args);
	}
	
//	 @Bean
//	    public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean() {
//	        FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//	        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
//
//	        filterRegistrationBean.setFilter(authorizationFilter);
//	        filterRegistrationBean.addUrlPatterns("/api/v1/users");
//
//	        return filterRegistrationBean;
//	    }

}

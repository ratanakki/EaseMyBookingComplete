package com.easemybooking.bookingdetails;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.easemybooking.bookingdetails.filter.AuthorizationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingdetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingdetailsApplication.class, args);
	}
	
//	 @Bean
//	    public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean() {
//	        FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//	        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
//
//	        filterRegistrationBean.setFilter(authorizationFilter);
//	        filterRegistrationBean.addUrlPatterns("/api/*");
//
//	        return filterRegistrationBean;
//	    }

}

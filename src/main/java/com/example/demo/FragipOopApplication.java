package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "users"})  // Tell Spring to scan "users"orderProcessingSys accountpayableSys
@ComponentScan(basePackages = {"com.example.demo", "orderProcessingSys"})
@ComponentScan(basePackages = {"com.example.demo", "accountpayableSys"})
@ComponentScan(basePackages = {"com.example.demo", "shippingSys"})
public class FragipOopApplication extends SpringBootServletInitializer{
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	  {
	  	return application.sources(FragipOopApplication.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(FragipOopApplication.class, args);
	}

}



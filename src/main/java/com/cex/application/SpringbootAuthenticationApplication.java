package com.cex.application;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAuthenticationApplication {

	public static void main(String[] args) {
//		System.out.println("Setting the timezone"+TimeZone.getTimeZone("GMT+9:00").getID());
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        
		SpringApplication.run(SpringbootAuthenticationApplication.class, args);
		
//		SpringApplication application = new SpringApplication(SpringbootAuthenticationApplication.class);
//		application.setAdditionalProfiles("home");
//	    application.run(args);
	}
}
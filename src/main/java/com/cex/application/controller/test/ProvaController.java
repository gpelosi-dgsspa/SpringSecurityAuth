package com.cex.application.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.config.properties.ConfigProperties;

@RestController
@RequestMapping("/prova")
public class ProvaController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/test")
	public ResponseEntity<String> test()
	{
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		log.info(cp.getDestinationPath());
		
		return new ResponseEntity<String>(cp.getDestinationPath(), HttpStatus.OK);
	}
	
}

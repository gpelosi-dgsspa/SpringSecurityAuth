package com.cex.application.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.app.AppService;
import com.cex.application.service.authentication.GerarchiaService;

@RestController
@RequestMapping("/app")
public class AppController 
{
	@Autowired
	private AppService appService;
	
	@Autowired
	private GerarchiaService gerarchiaService;
	
	@GetMapping("/environment")
	public ResponseEntity<List<String>> getEnvironment()
	{
		List<String> profiles = appService.getActiveEnvironments();
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(profiles, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get-hierarchy-source")
	public ResponseEntity<String> getHierarchySource()
	{
		String hierSource = gerarchiaService.getHierarchySource();
		ResponseEntity<String> response = new ResponseEntity<String>(hierSource, HttpStatus.OK);
		return response;
	}
}

package com.cex.application.service.app.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cex.application.service.app.AppService;

@Service
public class AppServiceImpl implements AppService
{

	@Autowired
	private Environment environment;
	
	@Override
	public List<String> getActiveEnvironments() {
		String[] profiles = environment.getActiveProfiles();
		return Arrays.asList(profiles);
	}

}

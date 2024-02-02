package com.cex.application.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties 
{
	@Value("${app.destination-path}")
	private String destinationPath;
	
	@Value("${app.random-initial-password}")
	private boolean randomInitialPassword;
	
	@Value("${app.initial-password}")
	private String initialPassword;
	
	@Value("${app.defaultpage}")
	private String defaultpage;
	
	@Value("${app.hierarchies-source}")
	private String hierarchiesSource;
	
	@Value("${app.roles-hierarchies:}")
	private String rolesHierarchies;
	
	@Value("${app.authorities-hierarchies:}")
	private String authoritiesHierarchies;

	public String getDestinationPath() {
		return destinationPath;
	}
	
	public boolean getRandomInitialPassword() {
		return randomInitialPassword;
	}

	public String getInitialPassword() {
		return initialPassword;
	}

	public String getDefaultpage() {
		return defaultpage;
	}

	public String getRolesHierarchies() {
		return rolesHierarchies;
	}

	public String getAuthoritiesHierarchies() {
		return authoritiesHierarchies;
	}

	public String getHierarchiesSource() {
		return hierarchiesSource;
	}

	@Override
	public String toString() {
		return "ConfigProperties [destinationPath=" + destinationPath + ", initialPassword=" + initialPassword
				+ ", defaultpage=" + defaultpage + ", hierarchiesSource=" + hierarchiesSource + ", rolesHierarchies="
				+ rolesHierarchies + ", authoritiesHierarchies=" + authoritiesHierarchies + "]";
	}
}

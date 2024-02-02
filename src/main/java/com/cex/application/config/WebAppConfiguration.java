package com.cex.application.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cex.application.config.properties.ConfigProperties;
import com.cex.application.util.AuthenticationUtil;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer 
{
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String SPRING_THYMELEAF_SUFFIX = "spring.thymeleaf.suffix";
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		
//		gestione per pagine sotto thymeleaf
		registry.addViewController("/").setViewName(cp.getDefaultpage());
//		registry.addViewController("/index").setViewName("index");
//		registry.addViewController("/subtemplates/subindex").setViewName("subtemplates/subindex");

//		Gestione dinamica delle pagine thymeleaf
		String webPageSuffix = env.getProperty(SPRING_THYMELEAF_SUFFIX);
		List<String> listaPagine = AuthenticationUtil.getAllApplicationPages(webPageSuffix);
		for(String pageName: listaPagine) {
			registry.addViewController("/"+pageName).setViewName(pageName);
		}
    }
}

package com.cex.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.cex.application.config.authentication.AuthUserDetailsService;
import com.cex.application.repository.authentication.GerarchiaRepository;
import com.cex.application.util.AuthenticationUtil;

@Configuration
public class ApplicationConfiguration 
{
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private GerarchiaRepository hierRepo;
	
	//---------- SINGOLO UTENTE SCOLPITO ----------------------------
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withUsername("user")
////				.username("user")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER")
////				.authorities("WRITE")
//				.disabled(false)
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	//---------- FINE SINGOLO UTENTE SCOLPITO ---------------------------- 
	
	
	//---------- UTENTE DB ----------------------------
	@Bean
    UserDetailsService userDetailsService() {
		return new AuthUserDetailsService();
    }
	//---------- FINE UTENTE DB ----------------------------
	
	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	@Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        Gestione manuale delle gerarchie applicative
//        String[] hierarchyList = {
//        		"ROLE_ADMINISTRATOR > ROLE_SUPER_USER > ROLE_USER", //gerarchia ruoli 
//        		"ADMIN > DELETE > WRITE > READ"  //gerarchia permessi
//        		};
        String[] hierarchyList = AuthenticationUtil.readHierarchyList(applicationContext, hierRepo);
        String hierarchy = String.join("\n", hierarchyList);
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
	
//	commentato sono stati rimossi da Spring gli warning afferenti alla mancata configurazione del CORS
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("*"));
//	    configuration.setAllowedMethods(Arrays.asList("*"));
//	    configuration.setAllowedHeaders(Arrays.asList("*"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
}

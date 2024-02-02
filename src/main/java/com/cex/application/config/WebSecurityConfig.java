package com.cex.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;

import com.cex.application.config.authentication.UserGrants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig 
{
	/**
	 * Se vengono settate più requestMatchers in sequenza per la stessa radice, per cui ci sarebbe un ovverwrite delle precedenti
	 * Spring mantiene la prima impostazione e non esegue l'overwrite
	 * 
	 * .requestMatchers("/path/test").authenticated()
	 * .requestMatchers("/path/**").hasAuthority(UserGrants.P_WRITE)
	 * 
	 * la seconda istruzione non sovrascrive la prima quindi tutti gli endpoint tranne /test saranno autenticati mediante authority P_WRITE, 
	 * cosa simile avviene nel seguente caso:
	 * 
	 * .requestMatchers("/path/**").hasAuthority(UserGrants.P_WRITE)
	 * .requestMatchers("/path/test").authenticated()
	 * 
	 * in cui la seconda istrunzione non sovrascrive la prima, quindi non ha alcun effetto e viene ignorata, di conseguenza
	 * tutti gli endpoint saranno autenticati mediante authority P_WRITE, anche /test
	 */
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
//			.cors(AbstractHttpConfigurer::disable)
//			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((authorize) -> authorize //requests
			.requestMatchers("/", "/home", "/index", "/login", "/register").permitAll()
			.requestMatchers("/users", "/user_detail", "/ruoli_permessi", "/logged_users").hasRole(UserGrants.R_ADMINISTRATOR)
//			.requestMatchers("/subtemplates/subindex", "/subtemplates/subhome").hasAnyRole(UserGrants.R_ADMINISTRATOR, UserGrants.R_SUPER_USER) //authenticated
			.requestMatchers("/subtemplates/subindex", "/subtemplates/subhome").hasAuthority(UserGrants.P_READ) //ROLE_**
//			.requestMatchers("/welcome").hasAnyAuthority(UserGrants.WRITE, UserGrants.ADMIN) //authenticated
//			.requestMatchers("/change_password").hasAuthority(UserGrants.READ) //authenticated
			.requestMatchers("/js/**", "/css/**", "/webjars/**").permitAll()
			.requestMatchers("/upload").hasRole(UserGrants.R_SUPER_USER)
			.requestMatchers("/upload/**").hasAuthority(UserGrants.P_WRITE)
			.requestMatchers("/utente/change-password-by-username/**").hasAuthority(UserGrants.P_READ)
			.requestMatchers("/utente/register", "/utente/get-current", "/utente/get-full-extended-current").permitAll()
			.requestMatchers("/utente/get-hierarchy-authorities").authenticated()
			.requestMatchers("/utente/**").hasAuthority(UserGrants.P_WRITE)
			.requestMatchers("/gerarchia/get-hierarchy-source").permitAll()
			.requestMatchers("/gerarchia/**").hasRole(UserGrants.R_ADMINISTRATOR)
//			.requestMatchers("/user-grant/**").hasAuthority(UserGrants.ADMIN) //authenticated
			.requestMatchers("/user-grant-web/**").hasAuthority(UserGrants.P_ADMIN)
			.requestMatchers("/ruolo-permesso/**").hasAuthority(UserGrants.P_ADMIN)
//			.requestMatchers("/user/**").hasRole("USER")
//			.requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
//			.requestMatchers(HttpMethod.POST, "/user/**").hasRole("USER")
			.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login").permitAll()
			)
			.logout((logout) -> logout.permitAll())
			.httpBasic(Customizer.withDefaults())
			.sessionManagement((sessionManagement) ->
				sessionManagement
					.sessionConcurrency((sessionConcurrency) ->
						sessionConcurrency
							.maximumSessions(1)
							.maxSessionsPreventsLogin(false)
							.sessionRegistry(sessionRegistry())
//							.expiredUrl("/invalid_session")
							.expiredUrl("/login?invalidsession")
					)
//					.invalidSessionUrl("/invalid_session")
			);
//			.sessionManagement(session -> session
////					.invalidSessionUrl("/invalid_session")
//					.maximumSessions(200)
//					.maxSessionsPreventsLogin(true)
//					.sessionRegistry(sessionRegistry())
//	        )
		
		return http.build();
	}

	@Bean
	SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
}
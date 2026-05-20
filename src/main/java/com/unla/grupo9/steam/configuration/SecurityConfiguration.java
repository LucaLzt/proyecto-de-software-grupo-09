package com.unla.grupo9.steam.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.unla.grupo9.steam.services.implementation.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsServiceImp userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	 BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
 	@Bean
 	 WebSecurityCustomizer webSecurityCustomizer() {
 		return (web) -> web.ignoring()
 		// Spring Security should completely ignore URLs starting with /resources/
 				.requestMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/json/**");
 	}
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/","/registro","/save").permitAll()
				.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/loginprocess")
						.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/loginsuccess")
						.permitAll()
				).logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll());
		return http.build();
	}
}

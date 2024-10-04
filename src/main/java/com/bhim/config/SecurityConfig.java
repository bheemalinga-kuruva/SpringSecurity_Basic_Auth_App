package com.bhim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	
	@Bean
	public InMemoryUserDetailsManager configureUsers() {
			
		   UserDetails adminUser = User.withDefaultPasswordEncoder()
										.username("bheema")
										.password("bheema@123")
										.authorities("ADMIN")
										.build();
			
			UserDetails normalUser = User.withDefaultPasswordEncoder()
										.username("rahul")
										.password("rahul@123")
										.authorities("USER")
										.build();
			
			return new InMemoryUserDetailsManager(adminUser, normalUser);
	}

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests((request) -> request
				.requestMatchers("/contact", "/products").permitAll()
				.anyRequest().authenticated()
		).formLogin();
		
		return http.build();
	}


}

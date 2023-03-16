package com.mobile.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	String[] allowURL = {
			"/api/v1/brands/**",
			"/api/v1/categories/**",
			"/api/v1/clothes/**"
	};
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
	    return new JwtAuthenticationFilter();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.cors()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET,allowURL)
					.permitAll()
				.requestMatchers(HttpMethod.POST,"/api/v1/auth/**")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
					.httpBasic();
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

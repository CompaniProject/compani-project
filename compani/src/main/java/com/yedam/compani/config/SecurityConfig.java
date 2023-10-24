package com.yedam.compani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
	@Bean
	public PasswordEncoder pwdEcd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.builder()
				.username("admin")
				.password(pwdEcd().encode("1234"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/home").hasRole("USER");
		return http.build();
	}
}

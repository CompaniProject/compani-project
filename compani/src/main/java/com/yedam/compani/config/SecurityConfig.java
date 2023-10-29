package com.yedam.compani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.antMatchers("/loginForm").permitAll();
			//.antMatchers("/home").hasRole("0C1");
//			.antMatchers("/").hasRole("0A2")
//			.antMatchers("/").hasRole("0A3")
//			.antMatchers("/").hasRole("0A4");
		http.formLogin() //인가.인증 실패하는경우 로그인페이지를 볼수있음
			.loginPage("/loginForm")
			.usernameParameter("loginId")
			.passwordParameter("loginPwd")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.failureUrl("/loginForm");
		http.csrf().disable();
		http.logout();
		
		return http.build();
	}
}

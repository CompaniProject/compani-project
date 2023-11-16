package com.yedam.compani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
	
	@Bean
	public CustomLoginSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	   @Bean
	   public PasswordEncoder getPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	   }
	
	
	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/loginForm").permitAll()
			.antMatchers("/standBy").permitAll()
			.antMatchers("/signUp").permitAll()
			.antMatchers("/memberSignUp").permitAll()
			.antMatchers("/companySignUp").permitAll()
			.antMatchers("/complete").permitAll()
			.antMatchers("/home").hasAnyRole("0A2", "0A3", "0A4")
			.antMatchers("/companyManager").hasAnyRole("0A2", "0A3")
			.antMatchers("/master-member").hasAnyRole("0A1")
			.anyRequest().authenticated();
			
		http.formLogin() //인가.인증 실패하는경우 로그인페이지를 볼수있음
			.loginPage("/loginForm")
			.usernameParameter("loginId")
			.passwordParameter("loginPwd")
			.loginProcessingUrl("/login")
			.successHandler(successHandler())
			.failureUrl("/loginForm")
			.permitAll();
		http.csrf().disable();
		http.logout();
		
		return http.build();
	}
	
	
	
	
//	 @Bean
//	  public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	    return new BCryptPasswordEncoder();
//	  }
	 
	 
}

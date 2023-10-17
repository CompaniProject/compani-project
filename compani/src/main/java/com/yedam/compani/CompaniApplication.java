package com.yedam.compani;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
@MapperScan(basePackages = "com.yedam.compani.**.mapper")
public class CompaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniApplication.class, args);
	}

	
}

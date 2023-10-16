package com.yedam.compani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CompaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}

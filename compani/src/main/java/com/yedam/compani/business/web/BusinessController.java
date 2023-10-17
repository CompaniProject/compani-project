package com.yedam.compani.business.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {
	@GetMapping("/project/business")
	public String projectHome() {
		return "project/business-home";
	}
}

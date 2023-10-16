package com.yedam.compani.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
	@GetMapping("/project/home")
	public String projectHome() {
		return "project/project-home";
	}
}

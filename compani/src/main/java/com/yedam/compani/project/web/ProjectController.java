package com.yedam.compani.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@Controller
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/project/home")
	public String projectHome() {
		return "project/project-home";
	}
	
	@GetMapping("/")
	public String projectList(Model model) {
		List<ProjectVO> list = projectService.getProjectList();
		model.addAttribute("projectList", list);
		return "index";
	}
}

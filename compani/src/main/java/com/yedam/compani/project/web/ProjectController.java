package com.yedam.compani.project.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping("home")
	public String projectList(Model model) {
		List<ProjectVO> list = projectService.getProjectList();
		model.addAttribute("projectList", list);
		return "home";
	}
	
	// session Test Controller
	// setAttribute -> login Controller로 이동 필요
	@GetMapping("/test")
	public String projectSidebar(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Map<Object,Object>> projectList = new ArrayList<>();
		projectList = projectService.getProjectAndMemberList();

		session.setAttribute("projectList", projectList);

		return "project/project-home";
	}
}

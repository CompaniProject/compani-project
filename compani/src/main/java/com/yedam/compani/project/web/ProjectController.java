package com.yedam.compani.project.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.project.member.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;
	@Autowired
	BusinessService businessService;
	@Autowired
	ProjectMemberService projectMemberService;

	@GetMapping("/project/home/{prjtNo}")
	public String projectHome(@PathVariable Integer prjtNo, Model model) {
		List<Map<Object,Object>> businessStateList = businessService.getBusinessStateList(prjtNo);
		List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		List<Map<Object,Object>> memberStatusList = projectMemberService.getBusinessCompleteStatus(prjtNo);

		model.addAttribute("businessStateList",businessStateList);
		model.addAttribute("businessLevelList",businessLevelList);
		model.addAttribute("memberStatusList",memberStatusList);

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

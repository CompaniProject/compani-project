package com.yedam.compani.project.feedback.web;

import com.yedam.compani.project.status.service.ProjectStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.project.status.service.ProjectStatusService;

@Controller
public class ProjectFeedbackController {
	
	@Autowired
	ProjectStatusService projectStatusService;
	
	@GetMapping("/project/feedback/{prjtNo}")
	public String projectFeedbackHome(@PathVariable int prjtNo, Model model) {
		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		model.addAttribute("projectStatus",projectStatus);
		return "project/feedback-home";
	}
}

package com.yedam.compani.project.feedback.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;

@Controller
public class ProjectFeedbackController {
	
	@Autowired
	ProjectStatusService projectStatusService;
	
	@Autowired
	ProjectFeedbackService projectFeedbackService;
	
	@GetMapping("/project/feedback/{prjtNo}")
	public String projectFeedbackHome(@PathVariable int prjtNo, Model model) {
		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		model.addAttribute("projectStatus",projectStatus);
		return "project/feedback-home";
	}
	
	// 프로젝트 이슈 피드백
	@GetMapping("/project/feedback/{prjtNo}/issue")
	public String projectFeedbackIssueList(@PathVariable int prjtNo, Model model) {
		List<IssueVO> list = projectFeedbackService.getProjectFeedbackIssueList();
		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		model.addAttribute("projectFeedbackIssueList", list);
		model.addAttribute("projectStatus",projectStatus);
		return "project/feedback-issue";
	}
	
}

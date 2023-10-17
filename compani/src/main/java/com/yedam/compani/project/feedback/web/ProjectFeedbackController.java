package com.yedam.compani.project.feedback.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectFeedbackController {
	// url 수정 필요
	@GetMapping("/project/feedback")
	public String projectFeedbackHome() {
		return "project/feedback-home";
	}
}

package com.yedam.compani.project.feedback.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectFeedbackController {

	private final ProjectStatusService projectStatusService;
	private final CompanyStatusService companyStatusService;
	
	@Autowired
	ProjectFeedbackService projectFeedbackService;
	
	@GetMapping("/project/feedback/{prjtNo}")
	public String projectFeedbackHome(@PathVariable int prjtNo, Model model) {
		// get session users company data
		String coCd = "CPN001";

		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		CompanyStatusVO cpnStatForCurrDt = companyStatusService.getStatusForCurrentDate(coCd);
		CompanyStatusVO cpnStatForPrjtDt = companyStatusService.getStatusForProjectDate(prjtNo,coCd);

		model.addAttribute("projectStatus",projectStatus);
		model.addAttribute("cpnStatForCurrDt",cpnStatForCurrDt);
		model.addAttribute("cpnStatForPrjtDt",cpnStatForPrjtDt);
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

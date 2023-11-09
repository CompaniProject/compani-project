package com.yedam.compani.project.feedback.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectFeedbackController {

	private final ProjectStatusService projectStatusService;
	private final CompanyStatusService companyStatusService;
	private final ProjectFeedbackService projectFeedbackService;
	private final ProjectService projectService;
	private final SessionService sessionService;
	
	@GetMapping("/project/feedback/{prjtNo}")
	public String projectFeedbackHome(@PathVariable int prjtNo, Model model, HttpServletRequest request) {
		// get session users company data
		MemberVO memberVO = sessionService.getLoginInfo(request);
		String coCd = memberVO.getCoCd();

		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		CompanyStatusVO cpnStatForCurrDt = companyStatusService.getStatusForCurrentDate(coCd);
		CompanyStatusVO cpnStatForPrjtDt = companyStatusService.getStatusForProjectDate(prjtNo,coCd);
		List<Map<Object,Object>> feedbackList = projectFeedbackService.getListForLevel(prjtNo);
		Map<Object, Object> projectVO = projectService.projectSelect(prjtNo);
		
		model.addAttribute("projectStatus",projectStatus);
		model.addAttribute("cpnStatForCurrDt",cpnStatForCurrDt);
		model.addAttribute("cpnStatForPrjtDt",cpnStatForPrjtDt);
		model.addAttribute("projectFeedbackList",feedbackList);
		model.addAttribute("projectVO", projectVO);

		return "project/feedback-home";
	}

	@PostMapping("/project/feedback")
	@ResponseBody
	public ProjectFeedbackVO insertProjectFeedback(@RequestBody ProjectFeedbackVO prjtFdbk, HttpServletRequest request){
		MemberVO memberVO = sessionService.getLoginInfo(request);
		prjtFdbk.setMembId(memberVO.getMembId());
		projectFeedbackService.insert(prjtFdbk);
		return prjtFdbk;
	}

	@PutMapping("/project/feedback")
	@ResponseBody
	public ProjectFeedbackVO updateProjectFeedback(@RequestBody ProjectFeedbackVO prjtFdbk) {
		projectFeedbackService.update(prjtFdbk);
		return prjtFdbk;
	}

}


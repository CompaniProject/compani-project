package com.yedam.compani.project.feedback.web;

import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackVO;
import com.yedam.compani.project.status.service.ProjectStatusVO;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yedam.compani.company.status.service.CompanyStatusService;
import com.yedam.compani.company.status.service.CompanyStatusVO;
import com.yedam.compani.project.feedback.service.ProjectFeedbackService;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectFeedbackController {

	private final ProjectStatusService projectStatusService;
	private final CompanyStatusService companyStatusService;
	private final ProjectFeedbackService projectFeedbackService;
	
	@GetMapping("/project/feedback/{prjtNo}")
	public String projectFeedbackHome(@PathVariable int prjtNo, Model model, HttpSession session) {
		// get session users company data
		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
		String coCd = memberVO.getCoCd();

		ProjectStatusVO projectStatus = projectStatusService.getProjectStatus(prjtNo);
		CompanyStatusVO cpnStatForCurrDt = companyStatusService.getStatusForCurrentDate(coCd);
		CompanyStatusVO cpnStatForPrjtDt = companyStatusService.getStatusForProjectDate(prjtNo,coCd);
		List<Map<Object,Object>> feedbackList = projectFeedbackService.getListForLevel(prjtNo);

		model.addAttribute("projectStatus",projectStatus);
		model.addAttribute("cpnStatForCurrDt",cpnStatForCurrDt);
		model.addAttribute("cpnStatForPrjtDt",cpnStatForPrjtDt);
		model.addAttribute("projectFeedbackList",feedbackList);
		model.addAttribute("prjtNo",prjtNo);

		return "project/feedback-home";
	}

	@PostMapping("/project/feedback")
	@ResponseBody
	public ProjectFeedbackVO insertProjectFeedback(@RequestBody ProjectFeedbackVO prjtFdbk, HttpSession session){
		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
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


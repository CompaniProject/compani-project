package com.yedam.compani.home.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final ProjectService projectService;
	private final BusinessService businessService;
	private final MemberFeedbackService memberFeedbackService;
	private final IssueService issueService;
	private final SessionService sessionService;
	
	@GetMapping("home")
	public String mainhomeList(Model model, HttpServletRequest request) {
	
		MemberVO memberVO = sessionService.getLoginInfo(request);
		List<ProjectVO> list = projectService.getProjectList(memberVO);
		model.addAttribute("projectList", list);
		List<MemberFeedbackVO> list2 = memberFeedbackService.getMemberFeedbackList(memberVO);
		model.addAttribute("memberFeedbackList", list2);
		List<BusinessVO> list3 = businessService.getBusinessList(memberVO);
		model.addAttribute("businessList", list3);
		List<IssueVO> list4 = issueService.getIssueList(memberVO);
		model.addAttribute("issueList", list4);

		return "home";
	}

	@PostMapping("ProjectStateAjax")
	@ResponseBody
	public Map<String, Object> ProjectStateAjax(ProjectVO projectVO, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<>();
		MemberVO memberVO = sessionService.getLoginInfo(request);
		projectVO.setMembId(memberVO.getMembId());
		List<ProjectVO> List = projectService.getProjectStateList(projectVO);
		map.put("projectStateList", List);
		
		return map;
	}

	@PostMapping("favAjax")
	@ResponseBody
	public Map<String, Object> favAjax(ProjectVO projectVO, HttpServletRequest request) {

		projectService.updateFavorite(projectVO);
		Map<String, Object> map = new HashMap<>();
		MemberVO memberVO = sessionService.getLoginInfo(request);
		projectVO.setMembId(memberVO.getMembId());

		List<ProjectVO> projectStateList = projectService.getProjectStateList(projectVO);
		map.put("project", projectStateList);
		
		return map;
	}
}

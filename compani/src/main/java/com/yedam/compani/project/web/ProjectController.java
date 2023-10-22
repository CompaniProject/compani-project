package com.yedam.compani.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.project.member.service.ProjectMemberService;
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
	@Autowired
	MemberFeedbackService memberFeedbackService;
	@Autowired
	IssueService issueService;

	@GetMapping("/project/home/{prjtNo}")
	public String projectHome(@PathVariable Integer prjtNo, Model model) {
		List<List<String>> businessStateList = businessService.getBusinessStateList(prjtNo);
		List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		List<Map<Object,Object>> memberStatusList = projectMemberService.getBusinessCompleteStatus(prjtNo);

		model.addAttribute("businessStateList",businessStateList);
		model.addAttribute("businessLevelList",businessLevelList);
		model.addAttribute("memberStatusList",memberStatusList);

		return "project/project-home";
	}
	
	@GetMapping("home")
	public String mainhomeList(Model model) {
		List<ProjectVO> list = projectService.getProjectList();
		model.addAttribute("projectList", list);
		List<MemberFeedbackVO> list2 = memberFeedbackService.getMemberFeedbackList();
		model.addAttribute("memberFeedbackList", list2);
		List<BusinessVO> list3 = businessService.getBusinessList();
		model.addAttribute("businessList", list3);
		List<IssueVO> list4 = issueService.getIssueList();
		model.addAttribute("issueList", list4);
		
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
	
	@PostMapping("ProjectStateAjax")
	@ResponseBody
	public Map<String, Object> ProjectStateAjax(ProjectVO projectVO) {

		Map<String, Object> map = new HashMap<>();
		System.out.println(projectVO.getPrjtSt());
		List<ProjectVO> List = projectService.getProjectStateList(projectVO);
		map.put("result", true);
		map.put("projectStateList", List);

		return map;
	}
	
	@PostMapping("favAjax")
	@ResponseBody
	public Map<String, Object> favAjax(ProjectVO projectVO) {

		int n = projectService.updateFavorite(projectVO);

		if (n >= 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}

		Map<String, Object> map = new HashMap<>();

		List<ProjectVO> projectStateList = projectService.getProjectStateList(projectVO);
		map.put("result", true);
		map.put("project", projectStateList);

		return map;
	}
	
	@GetMapping("projectheader")
	public String projectHeader(Model model) {
		
		
		return "layout/projectheader";
	}
	
	
}

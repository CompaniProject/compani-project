package com.yedam.compani.project.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.service.ProjectFormVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService projectService;
	private final BusinessService businessService;
	private final ProjectMemberService projectMemberService;
	private final MemberFeedbackService memberFeedbackService;
	private final IssueService issueService;
	private final MemberService memberService;

	@GetMapping("/project/home/{prjtNo}/{coCd}")
	public String projectHome(@PathVariable Integer prjtNo, @PathVariable String coCd, Model model, HttpServletRequest request) {
		List<List<String>> businessStateList = businessService.getBusinessStateList(prjtNo);
		List<Map<Object, Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		List<Map<Object, Object>> memberStatusList = projectMemberService.getBusinessCompleteStatus(prjtNo);
		
		request.getSession().setAttribute("prjtNo", prjtNo);

		model.addAttribute("businessStateList", businessStateList);
		model.addAttribute("businessLevelList", businessLevelList);
		model.addAttribute("memberStatusList", memberStatusList);

		// 헤더 단건 조회
		Map<Object, Object> projectVO = projectService.projectSelect(prjtNo);
		request.getSession().setAttribute("projectVO", projectVO);
		
		// 프로젝트 모달 수정 - 참여자 리스트
		List<Map<String,String>> prjtMemberList =  projectMemberService.projectMemberList(prjtNo);
		model.addAttribute("projectMemberList", prjtMemberList);
		// 프로젝트 모달 수정 - 회사 멤버 리스트
		List<MemberVO> memberList = memberService.prjtMemberList(prjtNo,coCd);
		model.addAttribute("memberList", memberList);
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

	@PostMapping("ProjectStateAjax")
	@ResponseBody
	public Map<String, Object> ProjectStateAjax(ProjectVO projectVO) {

		Map<String, Object> map = new HashMap<>();
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

	@GetMapping("/projectlayout")
	public String projectHeader(Model model) {

		return "layout/projectlayout";
	}

	//프로젝트 등록 - 실행시 
	@PostMapping("/insertProject")
	@ResponseBody
	public Map<String, Object> insertBusiness(@RequestBody ProjectFormVO formVO) {

		Map<String, Object> map = new HashMap<>();
		System.out.println(formVO);
		projectService.insertProject(formVO.getProject());
		projectMemberService.insertProjectMember(formVO);
		
		return map;

	}
}

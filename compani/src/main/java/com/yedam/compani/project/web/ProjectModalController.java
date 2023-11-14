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

import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.service.ProjectFormVO;
import com.yedam.compani.project.service.ProjectService;
import com.yedam.compani.project.service.ProjectVO;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;
/*
 * 
 * 작성자 : 신대철
 *   기능 : 프로젝트 등록 , 수정 , 프로젝트 정보
 * 
 **/

@Controller
@RequiredArgsConstructor
public class ProjectModalController {

	private final ProjectService projectService;
	private final ProjectMemberService projectMemberService;
	private final MemberService memberService;
	private final ProjectStatusService projectStatusService;
	private final SessionService sessionService;
	
	@GetMapping("project/modal/{prjtNo}")
	public String projectModal(@PathVariable Integer prjtNo, Model model, HttpServletRequest request) {
		
		// 프로젝트 등록,모달 수정 - 참여자 리스트
		List<Map<String,String>> prjtMemberList =  projectMemberService.projectMemberList(prjtNo);
		model.addAttribute("projectMemberList", prjtMemberList);
		
		// 프로젝트 정보 조회 
		MemberVO memberVO = sessionService.getLoginInfo(request);
		String membId = memberVO.getMembId();
		String coCd = memberVO.getCoCd();
		
		// 프로젝트 권한 체크 여부 
		ProjectMemberVO projectMemberVO = projectMemberService.projectMemberSelect(prjtNo, membId);	
		model.addAttribute("projectMemberVO", projectMemberVO);
		
		// 프로젝트 수정 시 회사 멤버 리스트 ( 생성자를 제외한)
		List<MemberVO> memberList = memberService.prjtMemberList(prjtNo,coCd);
		model.addAttribute("memberList", memberList);
		
		return "modal/modal-project";
	}
	
	//프로젝트 등록 - 실행시 
	@PostMapping("insertProject")
	@ResponseBody
	public Map<String, Object> insertProject(@RequestBody ProjectFormVO formVO, 
			                                 HttpServletRequest request) {

		Map<String, Object> map = new HashMap<>();
		projectService.insertProject(formVO.getProject());
		projectMemberService.insertProjectMember(formVO);

		// project 추가 후 sidebar project list 재설정
		sessionService.setProjectSidebarList(request);

		return map;
	}

	@PostMapping("updateProject")
	@ResponseBody
	public void updateProject(@RequestBody ProjectFormVO formVO, HttpServletRequest request) {
		ProjectVO projectVO = formVO.getProject();
		int prjtNo = formVO.getProject().getPrjtNo();
		projectService.updateProject(projectVO);

		// 프로젝트 완료 시, 통계 측정
		projectStatusService.insert(projectVO);
		
		projectMemberService.deleteProjectMember(formVO);
		projectMemberService.insertProjectMember(formVO);

		// 프로젝트 수정 시 사이드바 재설정
		sessionService.setProjectSidebarList(request);
		sessionService.setProjectInfo(prjtNo,request);
	}
	
}

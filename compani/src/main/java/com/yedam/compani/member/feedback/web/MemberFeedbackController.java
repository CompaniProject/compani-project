package com.yedam.compani.member.feedback.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;
import com.yedam.compani.project.status.service.ProjectStatusService;
import com.yedam.compani.project.status.service.ProjectStatusVO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class MemberFeedbackController {
	@Autowired
	BusinessMemberService service;
	@Autowired
	MemberService servicem;
	@Autowired
	ProjectStatusService serviceps;
	@Autowired
	MemberFeedbackService servicemf;
	
	
	
	@PostMapping("pfml")
	@ResponseBody
	public List<String> projectFeedbackMemberList(ProjectMemberVO prjtno){
		return servicem.getProjectFeedbackMemberList(prjtno);
	}
	
	@GetMapping("project/feedback/{prjtNo}/personal")
	public String projectFeedbackPersonal(@PathVariable int prjtNo, Model model, HttpSession session) {
		ProjectStatusVO projectStatus = serviceps.getProjectStatus(prjtNo);////////////////////////////////
		
		MemberFeedbackVO vo = new MemberFeedbackVO();
		
		model.addAttribute("projectStatus",projectStatus);
		model.addAttribute("pfst", servicem.getPersonalFeedbackStatusCnt(prjtNo));/////////////////////		
		return "member/feedbackPers";
	}
	
	@PostMapping("insertFeedBackPersonal")
	@ResponseBody
	public int setFeedbackPersonal(@RequestBody MemberFeedbackVO vo) {
		return servicemf.setFeedbackPersonal(vo);
	}
	
	@PostMapping("selectBusinessPersonal")
	@ResponseBody
	public List<Map<String, Object>> businessPersonal(@RequestBody Map<String, String> map){
		return servicemf.getBusinessPersonal(map);
	}
	
	@PostMapping("feedbackList")
	@ResponseBody
	public List<MemberFeedbackVO> feedbackList(@RequestBody MemberFeedbackVO vo){
		return servicemf.getFeedbackList(vo);
	}
	
	@PostMapping("feedbackPersonal")
	@ResponseBody
	public MemberFeedbackVO feedbackPersonal() {
		return servicemf.getFeedbackPersonal();
	}
	
	@PutMapping("editFeedbackPersonal")
	@ResponseBody
	public MemberFeedbackVO editFeedbackPersonal(@RequestBody MemberFeedbackVO vo) {
		servicemf.editFeedbackPersonal(vo);
		return vo;
	}
	
}

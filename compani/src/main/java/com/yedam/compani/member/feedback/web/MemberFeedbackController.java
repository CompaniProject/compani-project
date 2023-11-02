package com.yedam.compani.member.feedback.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.project.member.service.ProjectMemberVO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class MemberFeedbackController {
	@Autowired
	BusinessMemberService service;
	@Autowired
	MemberService servicem;
	
	//피드백
	@GetMapping("/fbpsn")
	public String businessFeedbackHome() {
		return "member/feedbackPers";
	}
	
	//피드백 총 업무 갯수
	@PostMapping("/BussCnt")
	@ResponseBody
	public int memberBusinessCnt(BusinessMemberVO vo) {
		return service.getMemberBusinessCnt(vo);
	}
	//피드백 상태에 따른 업무 갯수
	@PostMapping("/BussStCnt")
	@ResponseBody
	public Map<String,Integer> memberBusinessStCnt(@RequestBody List<Map<String, String>> list) {
		return service.getMemberBusinessStateCnt(list);
	}
	
	@PostMapping("/pfml")
	@ResponseBody
	public List<String> projectFeedbackMemberList(ProjectMemberVO prjtno){
		return servicem.getProjectFeedbackMemberList(prjtno);
	}
}

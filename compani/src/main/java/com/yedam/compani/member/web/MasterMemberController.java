package com.yedam.compani.member.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.session.service.SessionService;
/*
 * 김연규
 * 2023-11-03
 */
@Controller
public class MasterMemberController {
	@Autowired
	MemberService service;
	@Autowired
	SessionService sessionService;
	
	// 마스터 - 멤버 페이지
	@GetMapping("master-member")
	public String masterList(Model model) {
		List<Map<Object, Object>> memberList = service.masterMemberList();
		model.addAttribute("masterMemberList", memberList);
		return "master/master-member";
	}
	
	// 마스터 - 멤버 승인
	@PostMapping("updateMemberAccp")
	@ResponseBody
	public int updateMemberAccp(@RequestBody MemberVO vo){
		return service.updateMemberAccp(vo);
	}
	
	// 회사관리자 - 멤버 페이지
	@GetMapping("companyManager")
	public String companyManager(Model model, HttpServletRequest request) {
		MemberVO memberVO = sessionService.getLoginInfo(request);
		String coCd = memberVO.getCoCd();
		List<Map<Object, Object>> list = service.companyManager(coCd);
		model.addAttribute("companyManager", list);
		return "master/company-manager";
	}
}

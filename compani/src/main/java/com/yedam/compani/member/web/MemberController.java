package com.yedam.compani.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.service.ProjectVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	CompanyService serviceC;
	// 로그인 페이지
	@GetMapping("/loginForm")
	public String memberLoginForm() {
		return "member/memberLoginForm";
	}
	
	// 로그인
	@PostMapping("/login")
	public String memberLogin(@RequestParam String loginId, @RequestParam String loginPwd, Model model) {
		MemberVO loginVO = new MemberVO();
		loginVO.setMembId(loginId);
		loginVO.setMembPwd(loginPwd);
		loginVO = service.getMemberInfo(loginVO);
		if(loginVO.getMembId() == null) {
			return "member/memberLoginForm";
		}else if(loginVO.getMembAccp().equals("N")){
			return "member/memberStandBy";
		}else{
			model.addAttribute("loginInfo", loginVO);
			return "home";
		}
		
	}

	// 가입 후 대기
	@GetMapping("/standBy")
	public String memberStandByForm() {
		return "member/memberStandBy";
	}
	
	// 회원가입 유형
	@GetMapping("/signUp")
	public String signUpPage() {
		return "member/signUp";
	}
	
	// 사원 등록
	@GetMapping("/memberSignUp")
	public String memberSignUpForm() {
		return "member/memberSignUp";
	}

	
	//사원 가입 서브밋
	@PostMapping("/SignUpped")
	public String memberSignUpped(MemberVO joinVO, Model model) {
		if(service.setMemberInfo(joinVO)>0) {
			return "member/memberStandBy";
		}else {
			model.addAttribute("signUpInfo", joinVO);
			model.addAttribute("notice", "회원가입이 정상적으로 이루어지지 않았습니다.");
			return "member/memberSignUp";
		}
		
	}
	
	/*
	 * @GetMapping("business/home/{coCd}") public String memlist(Model model) {
	 * List<MemberVO> list = service.getMemberList();
	 * model.addAttribute("memberList", list); return "project/business-home" ; }
	 */
}

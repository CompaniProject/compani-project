package com.yedam.compani.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

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
	@GetMapping("/login")
	public String memberLogin(@RequestParam String loginId, @RequestParam String loginPwd, Model model) {
		MemberVO loginVO = new MemberVO();
		loginVO.setMembId(loginId);
		loginVO.setMembPwd(loginPwd);
		loginVO = service.getLogin(loginVO);
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
	
	// 기업 등록
	@GetMapping("/companySignUp")
	public String companySignUpForm() {
		return "member/companySignUp";
	}
}

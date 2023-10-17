package com.yedam.compani.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	// 로그인 페이지
	@GetMapping("/login")
	public String memberLoginForm() {
		return "member/memberLogin";
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

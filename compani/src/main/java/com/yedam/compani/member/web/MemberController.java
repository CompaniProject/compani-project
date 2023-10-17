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
}

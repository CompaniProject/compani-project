package com.yedam.compani.member.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.company.service.CompanyVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
    @Autowired
	CompanyService serviceC;

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String memberLoginForm() {
		return "member/memberLoginForm";
	}

	// 로그인
	@PostMapping("/login")
	public String memberLogin(MemberVO  vo, HttpSession session) {
		vo = service.getMemberInfo(vo);
		if (vo == null) {
			return "redirect:loginForm";
		} else if (vo.getMembAccp().equals("N")) {
			return "redirect:standBy";
		} else {
			session.setAttribute("loginInfo", vo);
			return "redirect:home";
		}

	}

	// 가입 후 대기
	@GetMapping("/standBy")
	public String memberStandByForm() {
		return "member/memberStandBy";
	}
	// 가입완료
	@GetMapping("/complete")
	public String memberSignUpComplete() {
		return "member/memberSignUpped";
	}

	// 회원가입 유형
	@GetMapping("/signUp")
	public String signUpPage() {
		return "member/signUp";
	}

	// 사원 등록 폼
	@GetMapping("/memberSignUp")
	public String memberSignUpForm(CompanyVO vo) {
		return "member/memberSignUp";
	}

	//아이디 중복체크용
	@PostMapping("/memberIdList")
	@ResponseBody
	public Map<String, Object> getMemberIdLists(){
		Map<String, Object> membIdList = new HashMap<>();
		membIdList.put("result", true);
		membIdList.put("data", service.getMemberIdList());
		return membIdList;
	}
	
	
	// 가입 서브밋
	@PostMapping("/SignUpped")
	public String memberSignUpped(MemberVO membVO, CompanyVO compVO, Model model) {
		if (membVO.getPermNo().equals("2")) {
			if (serviceC.setCompanyInfo(compVO) > 0) {
				if (service.setMemberInfo(membVO) > 0) {
					return "redirect:complete";
				} else {
					model.addAttribute("notice", "회원가입(사원부분)이 정상적으로 이루어지지 않았습니다.");
					return "memberSignUp";
				}
			} else {
				model.addAttribute("notice", "회원가입(기업부분)이 정상적으로 이루어지지 않았습니다.");
				return "memberSignUp";
			}
		} else {
			if (service.setMemberInfo(membVO) > 0) {
				return "redirect:complete";
			} else {
				model.addAttribute("notice", "회원가입(사원부분)이 정상적으로 이루어지지 않았습니다.");
				return "companySignUp";
			}
		}
	}
	
	

}

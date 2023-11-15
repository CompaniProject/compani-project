package com.yedam.compani.company.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.company.service.CompanyVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.extern.log4j.Log4j2;

/*
 * 이상길
 * 2023-11-15
 * 기업 등록 및 조회
 */

@Log4j2
@Controller
public class CompanyController {
	@Autowired
	CompanyService service;
	
	//회원가입 소속회사
	@PostMapping("companyList")
	@ResponseBody
	public Map<String, Object> companyList(){
		Map<String, Object> compList = new HashMap<>();
		compList.put("result", true);
		compList.put("data", service.getCompanyList());

		return compList;
	}

	
	// 기업 등록폼
	@GetMapping("companySignUp")
	public String companySignUpForm() {
		return "company/companySignUp";
	}
	
	//소속회사 코드생성후 사원회원가입폼으로
	@PostMapping("companySignUpped")
	public String companySignUp(CompanyVO vo, Model model) {
		vo.setCoCd(service.makeCompanyCode());
		return "member/memberSignUp";
	}
	
	//회사정보
	@PostMapping("companyInfo")
	@ResponseBody
	public CompanyVO companyInfo(CompanyVO vo){
		vo = service.getCompanyInfo(vo);
		return vo;
	}
}

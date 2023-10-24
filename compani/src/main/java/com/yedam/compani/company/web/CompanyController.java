package com.yedam.compani.company.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.company.service.CompanyVO;

@Controller
public class CompanyController {
	@Autowired
	CompanyService service;
	
	//회원가입 소속회사
	@PostMapping("/companyList")
	@ResponseBody
	public Map<String, Object> companyList(){
		Map<String, Object> compList = new HashMap<>();
		compList.put("result", true);
		compList.put("data", service.getCompanyList());

		return compList;
	}
	
	//회사코드생성
	@PostMapping("/makeCoCd")
	@ResponseBody
	public String makeCompanyCode(){
		return service.makeCompanyCode();
	}
	
	// 기업 등록폼
	@GetMapping("/companySignUp")
	public String companySignUpForm() {
		return "company/companySignUp";
	}
	
	//소속회사 코드생성후 사원회원가입폼으로
	@PostMapping("/companySignUpped")
	public String companySignUp(CompanyVO vo, Model model) {
		vo.setCoCd(service.makeCompanyCode());
		return "member/memberSignUp";
	}
}

package com.yedam.compani.company.web;

import java.util.List;

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
/*
 * 김연규
 * 2023-11-03
 * 마스터-회사관리
 */
@Controller
public class MasterCompanyController {
	@Autowired
	CompanyService service;
	@Autowired
	MemberService memberService;
	
	// 마스터 - 회사 페이지
	@GetMapping("master-company")
	public String companyAllList(Model model) {
		List<CompanyVO> companyList = service.companyAllList();
		model.addAttribute("masterCompanyList", companyList);
		return "master/master-company";
	}
	
	// 마스터 - 회사 승인
	@PostMapping("updateCompanyAccp")
	@ResponseBody
	public String updateCompanyAccp(@RequestBody CompanyVO vo) {
		// 회사 승인
		service.updateCompanyAccp(vo);
		// 회사 승인 시 멤버 자동 승인
		memberService.updateMemberAccpAuto(vo);
		return "";
	}
}

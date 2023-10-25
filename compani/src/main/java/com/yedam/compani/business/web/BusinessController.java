package com.yedam.compani.business.web;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
;

@Controller
@RequiredArgsConstructor
public class BusinessController {

	private final BusinessService businessService;
	private final MemberService memberService;
	
	@GetMapping("/project/business/{prjtNo}")
	public String projectHome(@PathVariable int prjtNo, Model model) {
		List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		model.addAttribute("businessLevelList",businessLevelList);
		
		List<MemberVO> list = memberService.getMemberList();
		model.addAttribute("memberList", list);
		
		return "project/business-home";
	}
}

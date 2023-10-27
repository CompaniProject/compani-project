package com.yedam.compani.business.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;
;

@Controller
@RequiredArgsConstructor
public class BusinessController {

	private final BusinessService businessService;
	@Autowired
	MemberService memberService;
	
	
	@GetMapping("/project/business/{prjtNo}")
	public String projectHome(@PathVariable int prjtNo, Model model) {
		List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		model.addAttribute("businessLevelList",businessLevelList);
		
		List<MemberVO> list = memberService.getMemberList();
		model.addAttribute("memberList", list);
		
		List<BusinessVO> busineesNameList =  businessService.bussinessNameList(prjtNo);
		System.out.println(busineesNameList);
		model.addAttribute("businessNameList", busineesNameList);
		return "project/business-home";
	}
	@PostMapping("insertBusiness")
	@ResponseBody
	public Map<String,Object>insertBusiness(@RequestBody BusinessVO businessVO) {
		
		Map<String, Object> map = new HashMap<>();
		
		if(businessService.insertBusiness(businessVO) >= 1) {
			map.put("insertResult", true);
			
			if(businessService.updateBusiness(businessVO)>= 1) {
				map.put("updateResult", true);
			}else {
				map.put("updateResult", false);
			}
		}else {
			map.put("insertResult", false);
		}
		return map;
	}
}

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

import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.business.service.FormVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;;

/*
 * 
 * 작성자 : 신대철
 * 작성일자:
 * 작업
 * 
 * */
@Controller
@RequiredArgsConstructor
@Slf4j
public class BusinessController {

	private final BusinessService businessService;
	private final MemberService memberService;
	private final BusinessMemberService businessMemberService;

	@GetMapping("/project/business/{prjtNo}")
	public String projectHome(@PathVariable int prjtNo, Model model) {
		List<Map<Object, Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		model.addAttribute("businessLevelList", businessLevelList);

		List<MemberVO> list = memberService.getMemberList();
		model.addAttribute("memberList", list);

		List<BusinessVO> busineesNameList = businessService.bussinessNameList(prjtNo);
		model.addAttribute("businessNameList", busineesNameList);
		return "project/business-home";
	}

	@PostMapping("insertBusiness")
	@ResponseBody
	public Map<String, Object> insertBusiness(@RequestBody FormVO formVO) {

		// ServiceImpl 로 옮기기**********************

		Map<String, Object> map = new HashMap<>();

		/*
		 * if (businessService.insertBusiness(formVO.getBusiness()) >= 1) {
		 * map.put("insertResult", true); if
		 * (!formVO.getBusiness().getBussDep().equals("")) { if
		 * (businessService.updateBusiness(formVO.getBusiness()) >= 1) {
		 * map.put("updateResult", true); } else { map.put("updateResult", false); } } }
		 * else { map.put("insertResult", false); }
		 */
		
		businessService.insertBusiness(formVO.getBusiness());
		businessMemberService.insertBusinessMember(formVO);
		
		//이거 한번 고민 해보자구 mapper 설계
		if (!formVO.getBusiness().getBussDep().equals("")) {
			businessService.updateBusiness(formVO.getBusiness());
		}

		return map;

	}

	@PostMapping("bussInfoAjax")
	@ResponseBody
	public Map<String, Object> bussInfo(BusinessVO businessVO, BusinessMemberVO businessMemberVO) {

		System.out.println("null이니" +businessMemberVO);
		Map<String, Object> map = new HashMap<>();
		
		//업무 단건 
		BusinessVO bussVO = businessService.businessSelect(businessVO);
		System.out.println(bussVO);
		map.put("businessVO", bussVO);
		
		//업무 참여자 list
		List<MemberVO> list = businessMemberService.bussMemberList(businessMemberVO); 
		map.put("businessMemberList", list);
		
		//회사 멤버 list
		List<MemberVO> memberList = memberService.getMemberList();
		map.put("memberList", memberList);
		 
		return map;
	}

	@GetMapping("home/modal")
	public String projectHome() {
		return "modal/modal-main";
	}

	@GetMapping("/businessInfo")
	public String businessInfo() {
		return "modal/modal-business";
	}

}

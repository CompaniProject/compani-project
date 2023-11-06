package com.yedam.compani.business.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.business.service.FormVO;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;;
;

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

		Map<String, Object> map = new HashMap<>();

		businessService.insertBusiness(formVO.getBusiness());
		businessMemberService.insertBusinessMember(formVO);

		// 이거 한번 고민 해보자구 mapper 설계
		if (!formVO.getBusiness().getBussDep().equals("")) {
			businessService.updateBusiness(formVO.getBusiness());
		}

		return map;

	}
	
	@GetMapping("home/modal")
	public String projectHome() {
		return "modal/modal-main";
	}

	@GetMapping("/businessInfo/{bussNo}")
	public String businessInfo(@PathVariable Integer bussNo ,Model model) {

		// 업무 단건
		BusinessVO bussVO = businessService.businessSelect(bussNo);
		model.addAttribute("businessVO", bussVO);
		
		// 업무 참여자 list
		List<MemberVO> list = businessMemberService.bussMemberList(bussNo);
		model.addAttribute("businessMemberList", list);
	
		// 회사 멤버 list
		List<MemberVO> memberList = memberService.getMemberList();
		model.addAttribute("memberList", memberList);

		return "modal/modal-business";
	}

	// 김연규, 2023-10-22, 개인 캘린더 업무리스트
	@GetMapping("myCalendar")
	public String personalCalendarList(Model model, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
		String membId = memberVO.getMembId();
		List<Map<Object,Object>> list = businessService.getPersonalCalendarBusinessList(membId);
		model.addAttribute("personalCalendarPage", list);
		return "calendar/personalCalendarPage";
	}
	
	// 김연규, 2023-10-22, 프로젝트 캘린더 업무리스트
	@GetMapping("projectCalendar")
	public String projectCalendarList(Model model, HttpSession session) {
		int prjtNo = (Integer) session.getAttribute("prjtNo");
		List<BusinessVO> list = businessService.getProjectCalenderBusinessList(prjtNo);
		model.addAttribute("projectCalendarPage", list);
		return "calendar/projectCalendarPage";
	}
	
	// 김연규, 2023-10-31, 캘린더&간트 업무바 수정
	@PostMapping("/updateCalendarBuss")
	@ResponseBody
	public String updatePersonalCalendarBuss(@RequestBody BusinessVO vo) {
		businessService.updateCalendarBuss(vo);
		return "";
	}
	// 김연규, 2023-11-01, 간트 상위업무 수정
	@PostMapping("/updateGanttUpcd")
	@ResponseBody
	public String updatePersonalGanttUpcd(@RequestBody BusinessVO vo) {
		businessService.updateGanttUpcd(vo);
		return "calendar/personalCalendarPage";
	}
}

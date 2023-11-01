package com.yedam.compani.business.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println(formVO.getBusiness());
		
		businessMemberService.insertBusinessMember(formVO);
		
		//이거 한번 고민 해보자구 
		if (!formVO.getBusiness().getBussDep().equals("")) {
			businessService.updateBusiness(formVO.getBusiness());
		}

		return map;

	}

	@PostMapping("bussInfoAjax")
	@ResponseBody
	public Map<String, Object> bussInfo(BusinessVO businessVO) {

		Map<String, Object> map = new HashMap<>();

		BusinessVO bussVO = businessService.businessSelect(businessVO);
		System.out.println(bussVO);
		map.put("businessVO", bussVO);

		return map;
	}

	@GetMapping("home/modal")
	public String projectHome() {
		return "modal/modal-main";
	}

	
	// 김연규, 2023-10-22, 개인 캘린더 업무리스트
	@GetMapping("personalCalendarPage")
	public String personalCalendarList(Model model, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
		String membId = memberVO.getMembId();
		List<Map<Object,Object>> list = businessService.getPersonalCalendarBusinessList(membId);
		model.addAttribute("personalCalendarPage", list);
		return "calendar/personalCalendarPage";
	}
	
	// 김연규, 2023-10-22, 프로젝트 캘린더 업무리스트
	@GetMapping("projectCalendarPage")
	public String projectCalendarList(Model model) {
		List<BusinessVO> list = businessService.getProjectCalenderBusinessList();
		model.addAttribute("projectCalendarPage", list);
		return "calendar/projectCalendarPage";
	}
	
	// 김연규, 2023-10-31, 캘린더&간트 업무바 수정
	@PostMapping("/updatePersonalCalendarBuss")
	@ResponseBody
	public String updatePersonalCalendarBuss(@RequestBody BusinessVO vo) {
		businessService.updateCalendarBuss(vo);
		return "calendar/personalCalendarPage";
	}
	@PostMapping("/updateProjectCalendarBuss")
	@ResponseBody
	public String updateProjectCalendarBuss(@RequestBody BusinessVO vo) {
		businessService.updateCalendarBuss(vo);
		return "calendar/projectCalendarPage";
	}
	// 김연규, 2023-11-01, 간트 상위업무 수정
	@PostMapping("/updatePersonalGanttUpcd")
	@ResponseBody
	public String updatePersonalGanttUpcd(@RequestBody BusinessVO vo) {
		businessService.updateGanttUpcd(vo);
		return "calendar/personalCalendarPage";
	}
	@PostMapping("/updateProjectGanttUpcd")
	@ResponseBody
	public String updateProjectGanttUpcd(@RequestBody BusinessVO vo) {
		businessService.updateGanttUpcd(vo);
		return "calendar/projectCalendarPage";
	}
}

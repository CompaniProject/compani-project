package com.yedam.compani.business.web;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
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
;

@Controller
@RequiredArgsConstructor
public class BusinessController {

	private final BusinessService businessService;
	
	@GetMapping("/project/business/{prjtNo}")
	public String projectHome(@PathVariable int prjtNo, Model model) {
		List<Map<Object,Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		model.addAttribute("businessLevelList",businessLevelList);
		
		return "project/business-home";
	}
	
	// 김연규, 2023-10-22, 개인달력 업무리스트
	@GetMapping("personalCalendarPage")
	public String personalCalendarList(Model model) {
		List<BusinessVO> list = businessService.getPersonalCalendarBusinessList();
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
	
	// 김연규, 2023-10-31, 캘린더 업무바 수정
	@PostMapping("/updatePersonalCalendarBuss")
	@ResponseBody
	public String updatePersonalCalendarBuss(@RequestBody BusinessVO vo) {
		businessService.updatePersonalCalendarBuss(vo);
		return "calendar/personalCalendarPage";
	}
	@PostMapping("/updateProjectCalendarBuss")
	@ResponseBody
	public String updateProjectCalendarBuss(@RequestBody BusinessVO vo) {
		businessService.updateProjectCalendarBuss(vo);
		return "calendar/projectCalendarPage";
	}
}

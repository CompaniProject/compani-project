package com.yedam.compani.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {
	
	@Autowired
	BusinessService businessService;
	
	// 개인달력 업무리스트
	@GetMapping("personalCalendarPage")
	public String personalCalendarList(Model model) {
		List<BusinessVO> list = businessService.getPersonalCalendarBusinessList();
		model.addAttribute("personalCalendarPage", list);
		return "calendar/personalCalendarPage";
	}
	
	// 프로젝트 캘린더 업무리스트
	@GetMapping("projectCalendarPage")
	public String projectCalendarList(Model model) {
		List<BusinessVO> list = businessService.getProjectCalenderBusinessList();
		model.addAttribute("projectCalendarPage", list);
		return "calendar/projectCalendarPage";
	}
}












package com.yedam.compani.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;

@Controller
public class CalendarController {
	
	@Autowired
	BusinessService businessService;
	
	// 개인달력 업무리스트
	@GetMapping("personalCalendarList")
	public String personalCalendarList(Model model) {
		List<BusinessVO> list = businessService.getPersonalCalendarBusinessList();
		model.addAttribute("personalCalendarList", list);
		return "calendar/personalCalendarPage";
	}
	
	@GetMapping("projectCalendarList")
	public String projectCalendarList(Model model) {
		List<BusinessVO> list = businessService.getProjectCalenderBusinessList();
		model.addAttribute("projectCalendarList", list);
		return "calendar/projectCalendarPage";
	}
}

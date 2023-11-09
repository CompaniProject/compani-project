package com.yedam.compani.business.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BusinessCalendarController {

	private final BusinessService businessService;
	private final SessionService sessionService;
	
	// 김연규, 2023-10-22, 개인 캘린더 업무리스트
	@GetMapping("myCalendar")
	public String personalCalendarList(Model model, HttpServletRequest request) {
		MemberVO memberVO = sessionService.getLoginInfo(request);
		String membId = memberVO.getMembId();
		List<Map<Object,Object>> list = businessService.getPersonalCalendarBusinessList(membId);
		model.addAttribute("personalCalendarPage", list);
		return "calendar/myCalendar";
	}
	
	// 김연규, 2023-10-22, 프로젝트 캘린더 업무리스트
	@GetMapping("project/calendar/{prjtNo}")
	public String projectCalendarList(Model model, @PathVariable int prjtNo) {
		// 캘린더 업무리스트
		List<BusinessVO> list = businessService.getProjectCalenderBusinessList(prjtNo);
		model.addAttribute("projectCalendarPage", list);
		
		return "calendar/projectCalendar";
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

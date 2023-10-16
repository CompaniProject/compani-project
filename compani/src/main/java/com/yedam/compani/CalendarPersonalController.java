package com.yedam.compani;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarPersonalController {
	
	@GetMapping("calendarPersonalList")
	public String calendar(Model model) {
		return "calendarPersonalList";
	}
}

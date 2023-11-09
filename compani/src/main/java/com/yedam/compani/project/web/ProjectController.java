package com.yedam.compani.project.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yedam.compani.session.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.project.member.service.ProjectMemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final BusinessService businessService;
	private final ProjectMemberService projectMemberService;
	private final SessionService sessionService;

	@GetMapping("/project/home/{prjtNo}")
	public String projectHome(@PathVariable Integer prjtNo, Model model, HttpServletRequest request) {
		List<List<String>> businessStateList = businessService.getBusinessStateList(prjtNo);
		List<Map<Object, Object>> businessLevelList = businessService.getBusinessAndLevelList(prjtNo);
		List<Map<Object, Object>> memberStatusList = projectMemberService.getBusinessCompleteStatus(prjtNo);
		
		model.addAttribute("businessStateList", businessStateList);
		model.addAttribute("businessLevelList", businessLevelList);
		model.addAttribute("memberStatusList", memberStatusList);

		sessionService.setProjectInfo(prjtNo, request);
		sessionService.checkProjectSt(prjtNo, request);
		sessionService.setProjectNo(prjtNo, request);

		return "project/project-home";
	}

}

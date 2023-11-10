package com.yedam.compani.business.reply.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PersonalReplyController {

	private final BusinessReplyService businessReplyService;
	private final SessionService sessionService;
	private final Integer AMOUNT = 8;
	
	@GetMapping("/personalReply/{prjtNo}")
	public String personalReplyPage(@PathVariable Integer prjtNo
									, @RequestParam(required = false, defaultValue = "1") int pageNum
									, Model model
									, HttpServletRequest request) {
		
		List<Map<Object,Object>> personalReplys = businessReplyService.getBusinessReply(
														sessionService.getLoginInfo(request).getMembId()
														,prjtNo
														,pageNum
														,AMOUNT);
		
		model.addAttribute("personalReplys", personalReplys);
		sessionService.setProjectInfo(prjtNo, request);
		sessionService.setProjectNo(prjtNo, request);
		
		return "project/personal-reply";
	}
	
	@GetMapping("personalReply")
	@ResponseBody
	public Map<String, Object> getPersonalReplyPage(int pageNum, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		MemberVO memberVO = sessionService.getLoginInfo(request);
		Integer prjtNo = sessionService.getProjectNo(request);
		
		List<Map<Object,Object>> personalReplys = businessReplyService.getBusinessReply(
														memberVO.getMembId()
														,prjtNo
														,pageNum
														,AMOUNT);
		
		map.put("replys", personalReplys);
		
		return map;
	}
}

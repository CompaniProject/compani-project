package com.yedam.compani.business.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.business.reply.service.BusinessReplyVO;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.session.service.SessionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PersonalReplyController {

	private final BusinessReplyService businessReplyService;
	private final SessionService sessionService;
	
	@GetMapping("/personalReply/{prjtNo}")
	public String personalReplyPage(@PathVariable Integer prjtNo, Model model, HttpServletRequest request) {
		
		MemberVO memberVO = sessionService.getLoginInfo(request);
		Integer pageNum = 1;
		Integer amount = 8;
		
		List<Map<Object,Object>> personalReplys = businessReplyService.getBusinessReply(
														memberVO.getMembId()
														,prjtNo
														,pageNum
														,amount);
		
		model.addAttribute("personalReplys", personalReplys);
		sessionService.setProjectInfo(prjtNo, request);
		
		return "project/personal-reply";
	}
}

package com.yedam.compani.business.reply.web;

import java.util.List;

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
		List<BusinessReplyVO> personalReplys = businessReplyService.getBusinessReply(memberVO.getMembId());
		model.addAttribute("personalReplys", personalReplys);
		
		return "project/personal-reply";
	}
}

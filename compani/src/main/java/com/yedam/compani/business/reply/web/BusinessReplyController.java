package com.yedam.compani.business.reply.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.business.reply.service.BusinessReplyVO;
import com.yedam.compani.member.service.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusinessReplyController {
	
	private final BusinessReplyService businessReplyService;
	
	@GetMapping("modal/reply")
	public String modalReply() {
		return "modal/modal-reply";
	}
	
	@RequestMapping("/business/reply/insert")
	@ResponseBody
	public BusinessReplyVO insertBusinessReply(@RequestBody BusinessReplyVO bussReply, HttpSession session){
		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
		bussReply.setMembId(memberVO.getMembId());
		businessReplyService.insert(bussReply);
		return bussReply;
	}

	@RequestMapping("/business/reply/update")
	@ResponseBody
	public BusinessReplyVO updateBusinessReply(@RequestBody BusinessReplyVO bussReply) {
		businessReplyService.update(bussReply);
		return bussReply;
	}

}

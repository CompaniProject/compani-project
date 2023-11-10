package com.yedam.compani.business.reply.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yedam.compani.session.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.business.reply.service.BusinessReplyVO;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusinessReplyController {
	
	private final BusinessReplyService businessReplyService;
	private final BusinessService businessService;
	private final SessionService sessionService;
	
	@GetMapping("modal/reply/{bussNo}")
	public String modalReply(@PathVariable Integer bussNo, Model model) {
		List<Map<Object,Object>> replyList = businessReplyService.getListForLevel(bussNo);
		BusinessVO businessVO = businessService.businessSelect(bussNo);
		
		model.addAttribute("replyList",replyList);
		model.addAttribute("business",businessVO);
		return "modal/modal-reply";
	}
	
	@PostMapping("/business/reply")
	@ResponseBody
	public BusinessReplyVO insertBusinessReply(@RequestBody BusinessReplyVO bussReply, HttpServletRequest request){
		MemberVO memberVO = sessionService.getLoginInfo(request);
		bussReply.setMembId(memberVO.getMembId());
		businessReplyService.insert(bussReply);
		return bussReply;
	}

	@PutMapping("/business/reply")
	@ResponseBody
	public BusinessReplyVO updateBusinessReply(@RequestBody BusinessReplyVO bussReply) {
		businessReplyService.update(bussReply);
		return bussReply;
	}

}

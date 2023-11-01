package com.yedam.compani.business.reply.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("modal/reply/{bussNo}")
	public String modalReply(@PathVariable Integer bussNo, Model model) {
		List<Map<Object,Object>> replyList = businessReplyService.getListForLevel(bussNo);
		model.addAttribute("replyList",replyList);
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

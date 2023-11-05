package com.yedam.compani.member.feedback.service;

import java.util.List;

import com.yedam.compani.member.service.MemberVO;

public interface MemberFeedbackService {
	public List<MemberFeedbackVO> getMemberFeedbackList(MemberVO memberVO);
}

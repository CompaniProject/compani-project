package com.yedam.compani.member.feedback.mapper;

import java.util.List;

import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberVO;

public interface MemberFeedbackMapper {
	public List<MemberFeedbackVO> getMemberFeedbackList(MemberVO memberVO);
}

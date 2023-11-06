package com.yedam.compani.member.feedback.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.service.MemberVO;

public interface MemberFeedbackService {
	public List<MemberFeedbackVO> getFeedbackList(MemberFeedbackVO vo);

	public int setFeedbackPersonal(MemberFeedbackVO vo);
	
	public List<Map<String, Object>> getBusinessPersonal(Map<String, String> map);
	
	public MemberFeedbackVO getFeedbackPersonal();
	
	public boolean editFeedbackPersonal(MemberFeedbackVO vo);
	public List<MemberFeedbackVO> getMemberFeedbackList(MemberVO memberVO);
}

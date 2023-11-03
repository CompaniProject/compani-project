package com.yedam.compani.member.feedback.service;

import java.util.List;
import java.util.Map;

public interface MemberFeedbackService {
	public List<MemberFeedbackVO> getMemberFeedbackList();

	public List<MemberFeedbackVO> getFeedbackList(MemberFeedbackVO vo);

	public int setFeedbackPersonal(MemberFeedbackVO vo);
	
	public List<Map<String, Object>> selectBusinessPersonal(Map<String, String> map);
}

package com.yedam.compani.member.feedback.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberVO;

public interface MemberFeedbackMapper {
	public List<MemberFeedbackVO> selectFeedbackList(MemberFeedbackVO vo);
	
	public int insertFeedbackPersonal(MemberFeedbackVO vo);
	
	public List<Map<String, Object>> selectBusinessPersonal(Map<String, String> map);
	
	public MemberFeedbackVO selectFeedbackPersonal();
	
	public int updateFeedbackPersonal(MemberFeedbackVO vo);
	public List<MemberFeedbackVO> getMemberFeedbackList(MemberVO memberVO);
}

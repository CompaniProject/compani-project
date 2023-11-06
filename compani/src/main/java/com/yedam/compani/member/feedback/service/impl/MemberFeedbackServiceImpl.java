package com.yedam.compani.member.feedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.member.feedback.mapper.MemberFeedbackMapper;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;
import com.yedam.compani.member.service.MemberVO;

@Service
public class MemberFeedbackServiceImpl implements MemberFeedbackService {

	@Autowired
	MemberFeedbackMapper memberFeedbackMapper;
	
	@Override
	public List<MemberFeedbackVO> getMemberFeedbackList(MemberVO memberVO) {
		
		return memberFeedbackMapper.getMemberFeedbackList(memberVO);
	}
	@Override
	public List<MemberFeedbackVO> getFeedbackList(MemberFeedbackVO vo) {
		return memberFeedbackMapper.selectFeedbackList(vo);
	}
	@Override
	public int setFeedbackPersonal(MemberFeedbackVO vo) {
		return memberFeedbackMapper.insertFeedbackPersonal(vo);
	}
	@Override
	public List<Map<String, Object>> getBusinessPersonal(Map<String, String> map) {
		return memberFeedbackMapper.selectBusinessPersonal(map);
	}
	@Override
	public MemberFeedbackVO getFeedbackPersonal() {
		return memberFeedbackMapper.selectFeedbackPersonal();
	}
	@Override
	public boolean editFeedbackPersonal(MemberFeedbackVO vo) {
		return (memberFeedbackMapper.updateFeedbackPersonal(vo) == 1);
	}
}

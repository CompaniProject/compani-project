package com.yedam.compani.member.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.member.feedback.mapper.MemberFeedbackMapper;
import com.yedam.compani.member.feedback.service.MemberFeedbackService;
import com.yedam.compani.member.feedback.service.MemberFeedbackVO;

@Service
public class MemberFeedbackServiceImpl implements MemberFeedbackService {

	@Autowired
	MemberFeedbackMapper memberFeedbackMapper;
	@Override
	public List<MemberFeedbackVO> getMemberFeedbackList() {
		
		return memberFeedbackMapper.getMemberFeedbackList();
	}

}

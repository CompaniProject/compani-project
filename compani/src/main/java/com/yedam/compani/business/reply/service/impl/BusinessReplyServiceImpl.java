package com.yedam.compani.business.reply.service.impl;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.reply.mapper.BusinessReplyMapper;
import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.business.reply.service.BusinessReplyVO;

@Service
@RequiredArgsConstructor
public class BusinessReplyServiceImpl implements BusinessReplyService {

	private final BusinessReplyMapper businessReplyMapper;
	
	@Override
	public List<BusinessReplyVO> getBusinessReply(String membId) {
		return businessReplyMapper.getBusinessReply(membId);
	}
	
	@Override
	public boolean insert(BusinessReplyVO projectFeedbackVO) {
		return (businessReplyMapper.insert(projectFeedbackVO) == 1);
	}
	
	@Override
	public boolean update(BusinessReplyVO projectFeedbackVO) {
		return (businessReplyMapper.update(projectFeedbackVO) == 1);
	}

	@Override
	public List<Map<Object, Object>> getListForLevel(int bussNo) {
		return businessReplyMapper.selectListForLevel(bussNo);
	}

}

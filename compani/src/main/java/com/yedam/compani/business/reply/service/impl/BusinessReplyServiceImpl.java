package com.yedam.compani.business.reply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.reply.mapper.BusinessReplyMapper;
import com.yedam.compani.business.reply.service.BusinessReplyService;
import com.yedam.compani.business.reply.service.BusinessReplyVO;

@Service
public class BusinessReplyServiceImpl implements BusinessReplyService {
	
	@Autowired
	BusinessReplyMapper businessReplyMapper;
	@Override
	public List<BusinessReplyVO> getBusinessReply() {

		return businessReplyMapper.getBusinessReply();
	}

}

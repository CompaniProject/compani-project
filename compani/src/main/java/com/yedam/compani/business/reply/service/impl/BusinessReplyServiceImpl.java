package com.yedam.compani.business.reply.service.impl;

import java.util.List;

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
	public List<BusinessReplyVO> getBusinessReply() {

		return businessReplyMapper.getBusinessReply();
	}

}

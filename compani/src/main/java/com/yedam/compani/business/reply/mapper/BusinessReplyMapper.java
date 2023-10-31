package com.yedam.compani.business.reply.mapper;

import java.util.List;

import com.yedam.compani.business.reply.service.BusinessReplyVO;

public interface BusinessReplyMapper {
	public List<BusinessReplyVO> getBusinessReply();
	public int insert(BusinessReplyVO projectFeedbackVO);
	public int update(BusinessReplyVO projectFeedbackVO);
}

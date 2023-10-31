package com.yedam.compani.business.reply.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.BusinessVO;

public interface BusinessReplyService {
	public List<BusinessReplyVO> getBusinessReply();
	public boolean insert(BusinessReplyVO projectFeedbackVO);
	public boolean update(BusinessReplyVO projectFeedbackVO);
	public List<Map<Object,Object>> getListForLevel(int bussNo);
}

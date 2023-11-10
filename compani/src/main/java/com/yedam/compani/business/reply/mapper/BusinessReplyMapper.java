package com.yedam.compani.business.reply.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.reply.service.BusinessReplyVO;

public interface BusinessReplyMapper {
	public List<BusinessReplyVO> getBusinessReply(String membId);
	public int insert(BusinessReplyVO projectFeedbackVO);
	public int update(BusinessReplyVO projectFeedbackVO);
	public List<Map<Object,Object>> selectListForLevel(int bussNo);
}

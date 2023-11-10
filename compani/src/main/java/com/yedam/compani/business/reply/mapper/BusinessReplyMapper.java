package com.yedam.compani.business.reply.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.reply.service.BusinessReplyVO;
import org.apache.ibatis.annotations.Param;

public interface BusinessReplyMapper {
	public List<Map<Object,Object>> getBusinessReply(	@Param("membId") String membId
													, @Param("prjtNo") Integer prjtNo
													, @Param("pageNum") Integer pageNum
													, @Param("amount") Integer amount);
	public int insert(BusinessReplyVO projectFeedbackVO);
	public int update(BusinessReplyVO projectFeedbackVO);
	public List<Map<Object,Object>> selectListForLevel(int bussNo);
}

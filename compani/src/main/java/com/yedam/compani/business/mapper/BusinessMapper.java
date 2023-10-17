package com.yedam.compani.business.mapper;

import java.util.List;

import com.yedam.compani.business.service.BusinessVO;

public interface BusinessMapper {
	// 캘린더 업무 전체조회
	public List<BusinessVO> selectPersonalCalendarAllBusiness();
}

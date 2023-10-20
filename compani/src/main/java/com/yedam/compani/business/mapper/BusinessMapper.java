package com.yedam.compani.business.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.BusinessVO;

public interface BusinessMapper {
	//개인 캘린더 업무리스트
	public List<BusinessVO> selectPersonalCalendarAllBusiness();
	
	// 프로젝트 캘린더 업무리스트
	public List<BusinessVO>	selectProjectCalendarAllBusiness();
	
	public List<Map<Object,Object>> selectBusinessStateList(Integer prjtNo);
	public List<Map<Object,Object>> selectBusinessAndLevelList(Integer prjtNo);
	public int updateProceedToDelay();
	public List<BusinessVO> getBusinessList();
}

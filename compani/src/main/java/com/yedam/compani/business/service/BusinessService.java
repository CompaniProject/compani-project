package com.yedam.compani.business.service;

import java.util.List;
import java.util.Map;

public interface BusinessService {
	// 개인 캘린더 업무리스트
	public List<Map<Object,Object>> getPersonalCalendarBusinessList(String membId);
	
	// 프로젝트 캘린더 업무리스트
	public List<BusinessVO> getProjectCalenderBusinessList();

	public List<List<String>> getBusinessStateList(Integer prjtNo);
	public List<Map<Object,Object>> getBusinessAndLevelList(Integer prjtNo);
	public int updateProceedToDelay();
	//업무 리스트 
	public List<BusinessVO> getBusinessList();
	public List<BusinessVO> bussinessNameList(Integer prjtNo);
	
	public int insertBusiness(BusinessVO businessVO);
	public int updateBusiness(BusinessVO businessVO);
	public BusinessVO businessSelect(BusinessVO businessVO);
	
	// 캘린더&간트 업무바 수정
	public int updateCalendarBuss(BusinessVO vo);
}

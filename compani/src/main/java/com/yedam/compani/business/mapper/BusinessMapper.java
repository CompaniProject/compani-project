package com.yedam.compani.business.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.BusinessVO;

public interface BusinessMapper {
	//개인 캘린더 업무리스트
	public List<Map<Object,Object>> selectPersonalCalendarAllBusiness(String membId);
	
	// 프로젝트 캘린더 업무리스트
	public List<BusinessVO>	selectProjectCalendarAllBusiness(Integer prjtNo);
	
	public List<Map<Object,Object>> selectBusinessStateList(Integer prjtNo);
	public List<Map<Object,Object>> selectBusinessAndLevelList(Integer prjtNo);
	public int updateProceedToDelay();
	public List<BusinessVO> getBusinessList();
	public List<BusinessVO> businessNameList(Integer prjtNo);
	public int insertBusiness(BusinessVO businessVO);
	public int updateBusiness(BusinessVO businessVO);
	public BusinessVO businessSelect(Integer bussNo);
	
	
	// 캘린더&간트 업무바 수정
	public int updateCalendarBuss(BusinessVO vo);
	// 간트 상위업무 수정
	public int updateGanttUpcd(BusinessVO vo);
}

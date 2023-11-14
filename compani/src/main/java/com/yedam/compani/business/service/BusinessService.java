package com.yedam.compani.business.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.service.MemberVO;

public interface BusinessService {
	// 개인 캘린더 업무리스트
	public List<Map<Object,Object>> getPersonalCalendarBusinessList(String membId);
	
	// 프로젝트 캘린더 업무리스트
	public List<BusinessVO> getProjectCalenderBusinessList(Integer prjtNo);

	public List<List<String>> getBusinessStateList(Integer prjtNo);
	public List<Map<Object,Object>> getBusinessAndLevelList(Integer prjtNo);
	public int updateProceedToDelay();
	//업무 리스트 
	public List<BusinessVO> getBusinessList(MemberVO memberVO);
	public List<BusinessVO> bussinessNameList(Integer prjtNo);
	public List<BusinessVO> businessdriveNameList(Integer prjtNo);
	public int insertBusiness(BusinessVO businessVO);
	public int updateBusiness(BusinessVO businessVO);
	public BusinessVO businessSelect(Integer bussNo);
	
	// 캘린더&간트 업무바 수정
	public int updateCalendarBuss(BusinessVO vo);
	// 간트 상위업무 수정
	public int updateGanttUpcd(BusinessVO vo);
	
	public int modifyBusiness(BusinessVO businessVO);
	public int updateRelation(FormVO formVO);
	public int resetRelation(BusinessVO businessVO);
	
	public List<BusinessVO>downRelationList(Integer bussNo);
	public int deleteBusiness(Integer bussNo);
	
}

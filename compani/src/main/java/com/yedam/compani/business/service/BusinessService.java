package com.yedam.compani.business.service;

import java.util.List;

public interface BusinessService {
	// 개인 캘린더 업무리스트
	public List<BusinessVO> getPersonalCalendarBusinessList();
	
	// 프로젝트 캘린더 업무리스트
	public List<BusinessVO> getProjectCalenderBusinessList();
}

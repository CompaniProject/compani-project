package com.yedam.compani.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.mapper.BusinessMapper;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	private BusinessMapper businessMapper;
	
	// 개인캘린더 업무리스트
	@Override
	public List<BusinessVO> getPersonalCalendarBusinessList() {
		return businessMapper.selectPersonalCalendarAllBusiness();
	}
	
	// 프로젝트 캘린더 업무리스트
	@Override
	public List<BusinessVO> getProjectCalenderBusinessList() {
		return businessMapper.selectProjectCalendarAllBusiness();
	}

}

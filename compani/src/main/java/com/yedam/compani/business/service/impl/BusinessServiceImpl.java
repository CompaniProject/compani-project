package com.yedam.compani.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.mapper.BusinessMapper;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	private BusinessMapper businessMapper;
	
	@Override
	public List<BusinessVO> getPersonalCalendarBusinessList() {
		return businessMapper.selectPersonalCalendarAllBusiness();
	}

	@Override
	public List<BusinessVO> getProjectCalenderBusinessList() {
		return businessMapper.selectProjectCalendarAllBusiness();
	}

	@Override
	public List<Map<Object, Object>> getBusinessStateList(Integer prjtNo) {
		return businessMapper.selectBusinessStateList(prjtNo);
	}

}

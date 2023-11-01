package com.yedam.compani.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.compani.business.mapper.BusinessMapper;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

	private final BusinessMapper businessMapper;
	
	// 개인캘린더 업무리스트
	@Override
	public List<Map<Object,Object>> getPersonalCalendarBusinessList(String membId) {
		return businessMapper.selectPersonalCalendarAllBusiness(membId);
	}
	
	// 프로젝트 캘린더 업무리스트
	@Override
	public List<BusinessVO> getProjectCalenderBusinessList() {
		return businessMapper.selectProjectCalendarAllBusiness();
	}

	@Override
	public List<List<String>> getBusinessStateList(Integer prjtNo) {
		List<List<String>> stateInfo = new ArrayList<>();
		List<Map<Object,Object>> stateList = businessMapper.selectBusinessStateList(prjtNo);
		List<String> states = new ArrayList<String>();
		List<String> stateCnts = new ArrayList<String>();

		for (Map<Object,Object> info: stateList) {
			states.add((String) info.get("bussSt"));
			stateCnts.add(String.valueOf(info.get("bussStCnt")));
		}

		stateInfo.add(states);
		stateInfo.add(stateCnts);

		return stateInfo;
	}

	@Override
	public List<Map<Object, Object>> getBusinessAndLevelList(Integer prjtNo) {
		return businessMapper.selectBusinessAndLevelList(prjtNo);
	}

	@Override
	public int updateProceedToDelay() {
		return businessMapper.updateProceedToDelay();
	}
	
	@Override
	public List<BusinessVO> getBusinessList() {
		return businessMapper.getBusinessList();
	}

	@Override
	public List<BusinessVO> bussinessNameList(Integer prjtNo) {
		
		return businessMapper.businessNameList(prjtNo);
	}

	@Override
	public int insertBusiness(BusinessVO businessVO) {
		
		return 	businessMapper.insertBusiness(businessVO);
	}

	@Override
	public int updateBusiness(BusinessVO businessVO) {
		
		return businessMapper.updateBusiness(businessVO);
		
	}

	@Override
	public BusinessVO businessSelect(BusinessVO businessVO) {
		
		return businessMapper.businessSelect(businessVO);
	}

	public int updatePersonalCalendarBuss(BusinessVO vo) {
		return businessMapper.updatePersonalCalendarBuss(vo);
	}
	
	@Override
	public int updateProjectCalendarBuss(BusinessVO vo) {
		return businessMapper.updateProjectCalendarBuss(vo);
	}
}

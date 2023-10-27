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
	public List<BusinessVO> getPersonalCalendarBusinessList() {
		return businessMapper.selectPersonalCalendarAllBusiness();
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
		
		///insert !
		//up % down 
		
		/*
		 * if( businessVO.getBussDep().equals("up")) {
		 * 
		 * //up 일경우 1. bussUpNm 의 업무번호 찾기 (select bussNo // FROM BUSINESS // WHERE
		 * bussUpNm = #{bussUpNm} // and prjtNo = #{prjtNo}} // 2. 찾은 업무 번호를 bussUpcd 로
		 * set 해주고 insert 하기 !
		 * 
		 * }else { //하위 업무 일경우 // 업무 2 insert => 업무 1 update // 하위업무의 Upcd 를 등록할 업무 번호로
		 * update // 1.선택한 업무의 기본키 가져오기 // select bussNo // FROM BUSINESS // WHERE
		 * bussUpNm = #{bussUpNm // and prjtNo = #{prjtNo}} // // 2. 업무 번호의 bussUpcd 를
		 * 등록할 업무 번호로 update
		 * 
		 * 
		 * }
		 */
		
		
		return 	businessMapper.insertBusiness(businessVO);
	}

	@Override
	public int updateBusiness(BusinessVO businessVO) {
		
		return businessMapper.updateBusiness(businessVO);
	}

}

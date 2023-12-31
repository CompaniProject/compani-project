package com.yedam.compani.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.compani.business.mapper.BusinessMapper;
import com.yedam.compani.business.service.BusinessService;
import com.yedam.compani.business.service.BusinessVO;
import com.yedam.compani.business.service.FormVO;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

	private final BusinessMapper businessMapper;

	// 개인캘린더 업무리스트
	@Override
	public List<Map<Object, Object>> getPersonalCalendarBusinessList(String membId) {
		return businessMapper.selectPersonalCalendarAllBusiness(membId);
	}

	// 프로젝트 캘린더 업무리스트
	@Override
	public List<BusinessVO> getProjectCalenderBusinessList(Integer prjtNo) {
		return businessMapper.selectProjectCalendarAllBusiness(prjtNo);
	}

	@Override
	public List<List<String>> getBusinessStateList(Integer prjtNo) {
		List<List<String>> stateInfo = new ArrayList<>();
		List<Map<Object, Object>> stateList = businessMapper.selectBusinessStateList(prjtNo);
		List<String> states = new ArrayList<String>();
		List<String> stateCnts = new ArrayList<String>();

		for (Map<Object, Object> info : stateList) {
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
	public List<BusinessVO> getBusinessList(MemberVO memberVO) {
		return businessMapper.getBusinessList(memberVO);
	}

	@Override
	public List<BusinessVO> bussinessNameList(Integer prjtNo) {

		return businessMapper.businessNameList(prjtNo);
	}

	@Override
	public int insertBusiness(BusinessVO businessVO) {

		return businessMapper.insertBusiness(businessVO);
	}

	@Override
	public int updateBusiness(BusinessVO businessVO) {

		return businessMapper.updateBusiness(businessVO);

	}

	@Override
	public BusinessVO businessSelect(Integer bussNo) {

		return businessMapper.businessSelect(bussNo);
	}

	// 캘린더&간트 업무바 수정
	@Override
	public int updateCalendarBuss(BusinessVO vo) {
		return businessMapper.updateCalendarBuss(vo);
	}

	// 간트 상위업무 수정
	@Override
	public int updateGanttUpcd(BusinessVO vo) {
		return businessMapper.updateGanttUpcd(vo);
	}

	@Override
	public int modifyBusiness(BusinessVO businessVO) {
		Date date = new Date();
		if(businessVO.getBussPrgre() < 100 && date.before(businessVO.getBussTodt()) ) {
			businessVO.setBussSt("0K1");
		}else if(businessVO.getBussPrgre() == 100 && date.after(businessVO.getBussTodt())) {
			businessVO.setBussSt("0K4");
		}else if(businessVO.getBussPrgre() < 100 && date.after(businessVO.getBussTodt())) {
			businessVO.setBussSt("0K3");
		}else {
			businessVO.setBussSt("0K2");
		}
		
		return businessMapper.modifyBusiness(businessVO);
	}
	
	@Override
	public List<BusinessVO> businessdriveNameList(Integer prjtNo) {

		return businessMapper.businessdriveNameList(prjtNo);
	}

	@Override
	public int updateRelation(FormVO formVO) {
		for(int i =0 ; i< formVO.getRelationList().size(); i++) {
			formVO.getRelationList().get(i).setBussNo(formVO.getBusiness().getBussNo());
		}
		return businessMapper.updateRelation(formVO.getRelationList());
	}

	@Override
	public List<BusinessVO> downRelationList(Integer bussNo) {
		
		return businessMapper.downRelationList(bussNo);
	}

	@Override
	public int resetRelation(BusinessVO businessVO) {
	
		return businessMapper.resetRelation(businessVO);
	}

	@Override
	public int deleteBusiness(Integer bussNo) {
		
		return businessMapper.deleteBusiness(bussNo);
	}

	@Override
	public int resetRelation(Integer bussNo) {
		BusinessVO businessVO = new BusinessVO();
		businessVO.setBussNo(bussNo);
		return businessMapper.resetRelation(businessVO);
	}





}

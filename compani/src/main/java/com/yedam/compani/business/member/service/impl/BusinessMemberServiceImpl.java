package com.yedam.compani.business.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.member.mapper.BusinessMemberMapper;
import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.business.service.FormVO;

@Service
public class BusinessMemberServiceImpl implements BusinessMemberService {

	@Autowired
	BusinessMemberMapper businessMemberMapper;

	@Override
	public BusinessMemberVO insertBusinessMember(FormVO formVO) {

		return businessMemberMapper.insertBusinessMember(formVO);
	}

	// 이것은 개인피드백에서 총 업무갯수를 찾는 SQL문 입니다.
	@Override
	public int getMemberBusinessCnt(BusinessMemberVO vo) {
		return businessMemberMapper.selectMemberBusinessCnt(vo);
	}

	@Override
	public Map<String, Integer> getMemberBusinessStateCnt(List<Map<String, String>> list) {
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < list.size(); i++) {
			map.put(list.get(i).get("bussSt"), businessMemberMapper.selectMemberBusinessStateCnt(list.get(i)));	
		}
		return map;
	}

}

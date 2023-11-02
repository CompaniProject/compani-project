package com.yedam.compani.business.member.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.service.FormVO;

public interface BusinessMemberService {
	public BusinessMemberVO insertBusinessMember(FormVO formVO);

	// 이것은 개인피드백에서 총 업무갯수를 찾는 SQL문 입니다.
	public int getMemberBusinessCnt(BusinessMemberVO vo);

	public Map<String, Integer> getMemberBusinessStateCnt(List<Map<String, String>> list);
}

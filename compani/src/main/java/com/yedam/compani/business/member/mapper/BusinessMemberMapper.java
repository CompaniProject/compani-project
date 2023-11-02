package com.yedam.compani.business.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.member.service.MemberVO;

public interface BusinessMemberMapper {
	public int insertBusinessMember(List<BusinessMemberVO> list);
	public List<MemberVO> bussMemberList(Integer bussNo);

	// 이것은 개인피드백에서 총 업무갯수를 찾는 SQL문 입니다.
	public int selectMemberBusinessCnt(BusinessMemberVO vo);

	public int selectMemberBusinessStateCnt(Map<String, String> map);
}

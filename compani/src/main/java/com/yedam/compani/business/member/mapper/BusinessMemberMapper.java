package com.yedam.compani.business.member.mapper;

import java.util.List;

import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.member.service.MemberVO;

public interface BusinessMemberMapper {
	public int insertBusinessMember(List<BusinessMemberVO> list);
	public List<MemberVO> bussMemberList(BusinessMemberVO businessMemberVO);
}

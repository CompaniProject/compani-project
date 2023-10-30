package com.yedam.compani.business.member.mapper;

import java.util.List;

import com.yedam.compani.business.member.service.BusinessMemberVO;

public interface BusinessMemberMapper {
	public int insertBusinessMember(List<BusinessMemberVO> list);
	public List<BusinessMemberVO> bussMemberList(BusinessMemberVO bmVO);
}

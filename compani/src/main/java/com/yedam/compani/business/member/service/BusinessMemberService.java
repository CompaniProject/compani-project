package com.yedam.compani.business.member.service;

import java.util.List;

import com.yedam.compani.business.service.FormVO;

public interface BusinessMemberService {
	public int insertBusinessMember(FormVO formVO);
	public List<BusinessMemberVO> bussMemberList(BusinessMemberVO bmVO);
}

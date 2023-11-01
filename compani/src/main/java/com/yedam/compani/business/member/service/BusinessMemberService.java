package com.yedam.compani.business.member.service;

import java.util.List;

import com.yedam.compani.business.service.FormVO;
import com.yedam.compani.member.service.MemberVO;

public interface BusinessMemberService {
	public int insertBusinessMember(FormVO formVO);
	public List<MemberVO> bussMemberList(Integer bussNo);
}

package com.yedam.compani.member.mapper;

import java.util.List;

import com.yedam.compani.member.service.MemberVO;

public interface MemberMapper {
	public MemberVO selectMemberInfo(MemberVO vo);
	
	public List<MemberVO> getMemberList();
}

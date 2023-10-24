package com.yedam.compani.member.service;

import java.util.List;

public interface MemberService {
	public MemberVO getLogin(MemberVO vo);
	
	public List<MemberVO> getMemberList();
	public MemberVO getMemberInfo(MemberVO vo);
	public int setMemberInfo(MemberVO vo);
}

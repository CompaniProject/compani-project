package com.yedam.compani.member.service;

import java.util.List;

public interface MemberService {
	public MemberVO getLogin(MemberVO vo);
	
	public List<MemberVO> getMemberList();
}

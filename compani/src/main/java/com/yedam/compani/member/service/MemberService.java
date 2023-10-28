package com.yedam.compani.member.service;

import java.util.List;

public interface MemberService {
	public MemberVO getMemberInfo(MemberVO vo);

	public int setMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberIdList();
}

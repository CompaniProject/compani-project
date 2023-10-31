package com.yedam.compani.member.service;

import java.util.List;

public interface MemberService {
	public List<MemberVO> getMemberList();

	public MemberVO getMemberInfo(MemberVO vo);

	public int setMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberIdList();

	public int editMemberInfo(MemberVO vo);
	
	public int editMemberPwd(MemberVO vo);
}

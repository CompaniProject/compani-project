package com.yedam.compani.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public List<MemberVO> getMemberList();

	public MemberVO getMemberInfo(MemberVO vo);

	public int setMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberIdList();
	public List<MemberVO> getMemberList(Map<String,String> map);

	public int editMemberInfo(MemberVO vo);
	
	public int editMemberPwd(MemberVO vo);
	public List<MemberVO> memberList(MemberVO vo);
	
}

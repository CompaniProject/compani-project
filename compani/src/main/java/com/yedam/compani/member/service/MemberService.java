package com.yedam.compani.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public List<MemberVO> getMemberList();
	public MemberVO getMemberInfo(MemberVO vo);
	public int setMemberInfo(MemberVO vo);
	public List<MemberVO> getMemberList(Map<String,String> map);
}

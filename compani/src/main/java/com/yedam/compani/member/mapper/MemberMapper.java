package com.yedam.compani.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.service.MemberVO;

public interface MemberMapper {
	public MemberVO selectMemberInfo(MemberVO vo);
	
	public List<MemberVO> getMemberList();
	public int insertMemberInfo(MemberVO vo);
	public List<MemberVO> memberSearchList(Map<String,String> map);
}

package com.yedam.compani.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.compani.member.service.MemberVO;

public interface MemberMapper {
	public MemberVO selectMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberList();

	public int insertMemberInfo(MemberVO vo);

	public List<MemberVO> selectMemberIdList();
	public List<MemberVO> memberSearchList(Map<String,String> map);

	public int updateMemberInfo(MemberVO vo);

	public int updateMemberPwd(MemberVO vo);
	public List<MemberVO> memberList(MemberVO vo);
}

package com.yedam.compani.member.service;

import java.util.List;
import java.util.Map;

import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface MemberService {
	public List<MemberVO> getMemberList();

	public MemberVO getMemberInfo(MemberVO vo);

	public int setMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberIdList();
	public List<MemberVO> getMemberList(Map<String,String> map);

	public int editMemberInfo(MemberVO vo);
	
	public int editMemberPwd(MemberVO vo);
	
	public List<String> getProjectFeedbackMemberList(ProjectMemberVO vo);
	public List<MemberVO> memberList(MemberVO vo);
	
	// 마스터 회원리스트
	public List<Map<Object, Object>> masterMemberList();
	
	// 마스터 멤버 승인
	public int updateMemberAccp(MemberVO vo);
}

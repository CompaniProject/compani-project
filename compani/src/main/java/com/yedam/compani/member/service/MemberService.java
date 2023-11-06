package com.yedam.compani.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface MemberService {
	public List<MemberVO> getMemberList();

	public MemberVO getMemberInfo(MemberVO vo);

	public int setMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberIdList();
	public List<MemberVO> getMemberList(Map<String,Object> map);

	public int editMemberInfo(MemberVO vo);
	
	public int editMemberPwd(MemberVO vo);
	
	public List<String> getProjectFeedbackMemberList(ProjectMemberVO vo);
	
	// 프로젝트 모달 등록 - 회사 멤버 리스트
	public List<MemberVO> memberList(MemberVO vo);
	// 프로젝트 모달 수정 - 회사 멤버 리스트(생성자 제외)
	public List<MemberVO> prjtMemberList(Integer prjtNo, String coCd);

	// 마스터 회원리스트
	public List<Map<Object, Object>> masterMemberList();
	public List<Map<String, String>> getPersonalFeedbackStatusCnt(int one);
}

package com.yedam.compani.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberVO;

public interface MemberMapper {
	public MemberVO selectMemberInfo(MemberVO vo);

	public List<MemberVO> getMemberList();

	public int insertMemberInfo(MemberVO vo);

	public List<MemberVO> selectMemberIdList();
	public List<MemberVO> memberSearchList(Map<String,Object> map);

	public int updateMemberInfo(MemberVO vo);
	public int updateMemberPwd(MemberVO vo);
	
	public  List<String> selectProjectFeedbackMemberList(ProjectMemberVO vo);
	public List<MemberVO> memberList(MemberVO vo);
	public List<MemberVO> prjtMemberList(@Param("prjtNo") Integer prjtNo, @Param("coCd") String coCd);
	
	// 마스터 회원리스트
	public List<Map<Object, Object>> masterMemberList();
}

package com.yedam.compani.member.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.member.mapper.MemberMapper;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
import com.yedam.compani.project.member.service.ProjectMemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper map;

	@Override
	public MemberVO getMemberInfo(MemberVO vo) {
		return map.selectMemberInfo(vo);
	}

	@Override
	public List<MemberVO> getMemberList() {

		return map.getMemberList();

	}
	@Override
	public int setMemberInfo(MemberVO vo) {
		return map.insertMemberInfo(vo);
	}

	@Override
	public List<MemberVO> getMemberIdList() {
		return map.selectMemberIdList();
	}

	@Override
	public List<MemberVO> getMemberList(Map<String, Object> list){

		return map.memberSearchList(list);
	}
	@Override
	public int editMemberInfo(MemberVO vo) {
		return map.updateMemberInfo(vo);
	}

	@Override
	public int editMemberPwd(MemberVO vo) {
		return map.updateMemberPwd(vo);
	}

	@Override
	public List<String> getProjectFeedbackMemberList(ProjectMemberVO vo) {
	
		return map.selectProjectFeedbackMemberList(vo);
	}
	@Override
	public List<MemberVO> memberList(MemberVO vo) {
		
		return map.memberList(vo);
	}

	@Override
	public List<MemberVO> prjtMemberList(Integer prjtNo, String coCd) {
		
		return map.prjtMemberList(prjtNo, coCd);
	}
		
	
	// 마스터 멤버리스트
	@Override
	public List<Map<Object, Object>> masterMemberList() {
		return map.masterMemberList();
	}
	
	@Override
	public List<Map<String, String>> getPersonalFeedbackStatusCnt(int one){
		return map.selectPersonalFeedbackStatusCnt(one);
	}
	

	// 마스터 멤버 승인
	@Override
	public int updateMemberAccp(MemberVO vo) {
		return map.updateMemberAccp(vo);
	}
	
}

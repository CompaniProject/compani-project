package com.yedam.compani.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.member.mapper.MemberMapper;
import com.yedam.compani.member.service.MemberService;
import com.yedam.compani.member.service.MemberVO;
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
	public int setMemberInfo(MemberVO vo) {
		return map.insertMemberInfo(vo);
	}

	@Override
	public List<MemberVO> getMemberIdList() {
		return map.selectMemberIdList();
	}
	
	
}

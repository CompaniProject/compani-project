package com.yedam.compani.member.service.impl;

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
	public MemberVO getLogin(MemberVO vo) {
		return map.selectMemberInfo(vo);
	}
	
}

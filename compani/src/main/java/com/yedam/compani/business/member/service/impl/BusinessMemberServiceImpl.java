package com.yedam.compani.business.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.member.mapper.BusinessMemberMapper;
import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.business.service.FormVO;

@Service
public class BusinessMemberServiceImpl implements BusinessMemberService{

	@Autowired
	BusinessMemberMapper businessMemberMapper;

	@Override
	public BusinessMemberVO insertBusinessMember(FormVO formVO) {
		
	
		return businessMemberMapper.insertBusinessMember(formVO);
	}

	
	

}

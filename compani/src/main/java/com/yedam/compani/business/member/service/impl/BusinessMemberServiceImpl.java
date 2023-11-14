package com.yedam.compani.business.member.service.impl;

import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.business.member.mapper.BusinessMemberMapper;
import com.yedam.compani.business.member.service.BusinessMemberService;
import com.yedam.compani.business.member.service.BusinessMemberVO;
import com.yedam.compani.business.service.FormVO;
import com.yedam.compani.member.service.MemberVO;

@Service
public class BusinessMemberServiceImpl implements BusinessMemberService {

	@Autowired
	BusinessMemberMapper businessMemberMapper;

	@Override
	public int insertBusinessMember(FormVO formVO) {
		
		for(int i =0 ; i< formVO.getBusinessMember().size(); i++) {
			formVO.getBusinessMember().get(i).setPrjtNo(formVO.getBusiness().getPrjtNo());
			formVO.getBusinessMember().get(i).setBussNo(formVO.getBusiness().getBussNo());
		}
	
		return businessMemberMapper.insertBusinessMember(formVO.getBusinessMember());
	}

	@Override
	public List<MemberVO> bussMemberList(Integer bussNo) {
		
		return businessMemberMapper.bussMemberList(bussNo);
	}


	@Override
	public int deleteBusinessMember(FormVO formVO) {
		
		int bussNo = formVO.getBusiness().getBussNo();
		return businessMemberMapper.deleteBusinessMember(bussNo);
	}

	@Override
	public int clearBusinessMember(Integer bussNo) {
	
		return businessMemberMapper.deleteBusinessMember(bussNo);
	}
}

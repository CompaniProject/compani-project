package com.yedam.compani.business.service;

import java.util.List;

import com.yedam.compani.business.member.service.BusinessMemberVO;

import lombok.Data;
@Data
public class FormVO {
	BusinessVO business;
	List<BusinessMemberVO> businessMember;
}

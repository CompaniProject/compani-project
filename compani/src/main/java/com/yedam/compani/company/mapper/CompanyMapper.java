package com.yedam.compani.company.mapper;

import java.util.List;

import com.yedam.compani.company.service.CompanyVO;

public interface CompanyMapper {
//	회원가입 소속회사
	public List<CompanyVO> selectCompanyList();
	
	//회사 가입
	public int InsertCompanyInfo(CompanyVO vo);
}

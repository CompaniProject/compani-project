package com.yedam.compani.company.service;

import java.util.List;

public interface CompanyService {
//	회원가입 소속회사
	public List<CompanyVO> getCompanyList();

	//회사코드 생성
	public String makeCompanyCode();
	
	// 회사 가입
	public int setCompanyInfo(CompanyVO vo);
	
	public CompanyVO getCompanyInfo(CompanyVO vo);
}

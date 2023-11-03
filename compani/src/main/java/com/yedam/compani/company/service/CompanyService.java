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
	
	// 마스터 회사리스트
	public List<CompanyVO> companyAllList();
	
	// 마스터 회사 승인
	public int updateCompanyAccp(CompanyVO vo);
}

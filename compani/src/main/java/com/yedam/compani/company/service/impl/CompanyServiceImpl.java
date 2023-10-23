package com.yedam.compani.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.company.mapper.CompanyMapper;
import com.yedam.compani.company.service.CompanyService;
import com.yedam.compani.company.service.CompanyVO;
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyMapper map;
//	회원가입 소속회사
	@Override
	public List<CompanyVO> getCompanyList() {
		return map.selectCompanyList();
	}
	@Override
	public int setCompanyInfo(CompanyVO vo) {
		return map.InsertCompanyInfo(vo);
	}

}

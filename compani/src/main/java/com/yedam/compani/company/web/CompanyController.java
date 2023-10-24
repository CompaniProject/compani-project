package com.yedam.compani.company.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.compani.company.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	CompanyService service;
	
	//회사목록
	@PostMapping("/companyList")
	@ResponseBody   
	public Map<String, Object> companyList(){
		Map<String, Object> compList = new HashMap<>();
		compList.put("result", true);
		compList.put("data", service.getCompanyList());

		return compList;
	}
}

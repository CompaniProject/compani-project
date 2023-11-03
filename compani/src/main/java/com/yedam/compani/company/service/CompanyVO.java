package com.yedam.compani.company.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CompanyVO {
	private String coCd;
	private String coNm;
	private String coRpstr;
	private String coBizno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date coDt;
	private String coAccp;
}

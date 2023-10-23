package com.yedam.compani.company.service;

import java.sql.Date;

import lombok.Data;

@Data
public class CompanyVO {
	private String coCd;
	private String coNm;
	private String coRpstr;
	private String coBizno;
	private Date coDt;
}

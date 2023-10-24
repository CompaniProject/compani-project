package com.yedam.compani.company.status.service;

import java.sql.Date;

import lombok.Data;

@Data
public class CompanyStatusVO {
	private Date coStatDt;
	private String coCd;
	private int mmBussCmplCnt;
	private int mmBussUncmplCnt;
	private int mmBussExcecmplCnt;
	private int mmIssuSolveCnt;
	private int mmIssuUnsolveCnt;
	private int mmIssuUnsolvableCnt;
	private int mmBussCmplRate;
	private int mmBussUncmplRate;
	private int mmBussExcecmplRate;
	private int mmIssuSolveRate;
	private int mmIssuUnsolveRate;
	private int mmIssuUnsolvableRate;
}

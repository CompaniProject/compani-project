package com.yedam.compani.project.status.service;

import lombok.Data;

@Data
public class ProjectStatusVO {
	private int prjtNo;
	private int bussCmplCnt;
	private int bussUncmplCnt;
	private int bussExceCnt;
	private int issuSolveCnt;
	private int issuUnsolveCnt;
	private int issuUnsolvableCnt;
	private float bussCmplRate;
	private float bussUncmplRate;
	private float bussExceRate;
	private float issuSolveRate;
	private float issuUnsolveRate;
	private float issuUnsolvableRate;
	private int prjtStatDtCnt;
	private int prjtMembCnt;
	private int bussCnt;
	private int issuCnt;
}

package com.yedam.compani.projectstatus.service;

import lombok.Data;

@Data
public class ProjectStatusVO {
	private int prjtNo;
	private int busscmplCnt;
	private int bussuncmplCnt;
	private int bussexceCnt;
	private int issusolveCnt;
	private int issuunsolveCnt;
	private int issuunsolvableCnt;
	private int busscmplRate;
	private int bussuncmplRate;
	private int bussexceRate;
	private int issusolveRate;
	private int issuunsolveRate;
	private int issuunsolvableRate;
}

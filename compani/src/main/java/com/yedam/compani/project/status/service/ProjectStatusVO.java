package com.yedam.compani.project.status.service;

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
	private float busscmplRate;
	private float bussuncmplRate;
	private float bussexceRate;
	private float issusolveRate;
	private float issuunsolveRate;
	private float issuunsolvableRate;
	private int prjtStatDtCnt;
	private int prjtMembCnt;
	private int prjt_stat_dt_cnt;
	private int prjt_memb_cnt;
}

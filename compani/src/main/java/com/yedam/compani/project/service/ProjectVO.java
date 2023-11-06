package com.yedam.compani.project.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProjectVO {
	private int prjtNo;
	private	String coCd;
	private String prjtNm;
	private String prjtPubcyn;
	private Date prjtFdt;
	private Date prjtTdt;
	private Date prjtCmplTdt;
	private String prjtSt;	// OD1 : 진행중, 0D2 : 완료, 0D3 : 보류
	private String prjtStNm;
	private int count;
	private String prjtFav;
	private String membId;
	
//	public boolean isStateEnd() {
//		return (prjtSt.equals("0D2"));
//	}
}

package com.yedam.compani.project.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectVO {
	private int prjtNo;
	private	String coNo;
	private String prjtNm;
	private String prjtPubcyn;
	private Date prjtFdt;
	private Date prjtTdt;
	private Date prjtCmplTdt;
	private String prjtSt;
}

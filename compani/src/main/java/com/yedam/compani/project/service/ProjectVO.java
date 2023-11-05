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
	private String prjtSt;
	private String prjtStNm;
	private int count;
	private String prjtFav;
}

package com.yedam.compani.business.service;

import java.sql.Date;

import lombok.Data;

@Data
public class BusinessVO {
	private int bussNo;
	private int prjtNo;
	private int bussUpcd;
	private String bussNm;
	private String bussImp;
	private int bussPrgre;
	private Date bussFrdt;
	private Date bussTodt;
	private Date bussCmpltdt;
	private String bussSt;
	private String prjtNm;
	private String bussDep;
	private String bussUpNm;
	private String bussStNm;
}

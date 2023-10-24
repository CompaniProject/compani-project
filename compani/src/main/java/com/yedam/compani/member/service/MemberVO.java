package com.yedam.compani.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	private String membId;
	private String membPwd;
	private String membNm;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date membBirthDt;
	private String membEmail;
	private String membTelno;
	private String coCd;
	private String membDept;
	private String membWkgd;
	private String membPhtPath;
	private String membMsg;
	private String membAccp;
	private String permNo;
}

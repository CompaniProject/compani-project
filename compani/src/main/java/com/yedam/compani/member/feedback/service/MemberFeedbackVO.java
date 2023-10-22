package com.yedam.compani.member.feedback.service;

import java.util.Date;

import lombok.Data;

@Data
public class MemberFeedbackVO {
	private int membFdbkNo;
	private String membFdbkWriter;
	private String membFdbkCntn;
	private Date membFdbkDt;
	private String membFdbkObj;
	private int prjtNo;
	private String prjtNm;
}

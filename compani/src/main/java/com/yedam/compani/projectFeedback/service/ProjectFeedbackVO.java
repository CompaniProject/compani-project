package com.yedam.compani.projectFeedback.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectFeedbackVO {
	private int prjtFdbkNo;
	private int prjtNo;
	private String membId;
	private String prjtFdbkCntn;
	private Date prjtFdbkDt;
}

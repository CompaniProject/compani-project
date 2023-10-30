package com.yedam.compani.project.feedback.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectFeedbackVO {
	private int prjtFdbkNo;
	private int prjtNo;
	private String membId;
	private String prjtFdbkCntn;
	private Date prjtFdbkDt;
	private Integer prjtFdbkUpno;
	private String prjtFdbkShow;
}

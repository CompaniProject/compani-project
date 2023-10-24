package com.yedam.compani.question.service;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionVO {
	private int qstNo;
	private String qstTtl;
	private String qstCntn;
	private Date qstDt;
	private String qstSt;
	private String membId;
}

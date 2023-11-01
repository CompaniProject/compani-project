package com.yedam.compani.issue.reply.service;

import java.util.Date;

import lombok.Data;

@Data
public class IssueReplyVO {
	private int issuRplyNo;
	private int issuNo;
	private String membId;
	private String issuRplyCntn;
	private Date issuRplyDt; // 등록 일자
	private int issuRplyDel; // 삭제 여부

	private String membNm;
}

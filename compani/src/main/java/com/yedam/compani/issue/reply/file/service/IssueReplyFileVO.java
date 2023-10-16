package com.yedam.compani.issue.reply.file.service;

import java.util.Date;

import lombok.Data;

@Data
public class IssueReplyFileVO {
	private int prjtNo;
	private int logJno;
	private String logJcd;
	private Date logDt;
	private String membId;
	private String logCntn;
}

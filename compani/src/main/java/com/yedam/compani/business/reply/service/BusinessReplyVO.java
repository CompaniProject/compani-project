package com.yedam.compani.business.reply.service;

import java.sql.Date;

import lombok.Data;

@Data
public class BusinessReplyVO {
	private int replyNo;
	private int bussNo;
	private String replyCntn;
	private Date replyDt;
	private String pubcyn;
	private String membId;
	private Integer replyUpno;
}

package com.yedam.compani.notice.service;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int notiNO;
	private String notiTtl;
	private String notiCntn;
	private Date notiDt;
	private int notiInq;
}

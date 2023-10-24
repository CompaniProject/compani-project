package com.yedam.compani.notification.service;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationVO {
	private int notiNo;
	private String membId;
	private String notiMsg;
	private int notiConno;
	private Date notiDt;
	private String notiYn;
	private String notiCd;
}

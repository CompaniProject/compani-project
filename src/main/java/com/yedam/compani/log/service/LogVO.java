package com.yedam.compani.log.service;

import java.util.Date;

import lombok.Data;

@Data
public class LogVO {
	private int prjtNo;
	private int logNo;
	private String logJcd;
	private Date logDt;
	private String membId;
	private String logCntn;
}

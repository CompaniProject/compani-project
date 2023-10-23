package com.yedam.compani.issue.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class IssueVO {
	private int issuNo;
	private int prjtNo;
	private int bussNo;
	private String membId;
	private String issuTtl;
	private String issuCntn;
	private String issuKnd;
	private String issuRank;
	private String issuSt;
	private Date issuDt;
	private String bussNm;
	private String prjtNm;
	
	private List<MultipartFile> files;
}

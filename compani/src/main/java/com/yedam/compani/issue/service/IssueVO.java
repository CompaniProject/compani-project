package com.yedam.compani.issue.service;

import java.util.ArrayList;
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
	private List<MultipartFile> editFiles;
	private List<Integer> removeFileIds = new ArrayList<>(); // 삭제할 첨부파일 issuFileNo List
	private String membNm;
	private	String membPhtPath;
	private String filterType;
}

package com.yedam.compani.file.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class FileVO {
	private int fileNo;
	private Integer prjtNo;
	private Integer bussNo;
	private String fileNm;
	private String filePath;
	private String fileType;
	private String fileDesct;
	private Date fileDt;
	private String membId;
	private Integer fileSize;

}

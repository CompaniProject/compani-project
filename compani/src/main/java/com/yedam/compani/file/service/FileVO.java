package com.yedam.compani.file.service;

import java.util.Date;

import lombok.Data;
@Data
public class FileVO {
	private Integer fileNo;
	private Integer prjtNo;
	private Integer bussNo;
	private String fileNm;
	private String filePath;
	private String fileType;
	private String fileDesct;
	private Date fileDt;
	private String membId;
}

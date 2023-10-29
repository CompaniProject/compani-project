package com.yedam.compani.file.service;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDTO {
	private int fileNo;
	private Integer prjtNo;
	private Integer bussNo;
	private String fileNm;
	private String filePath;
	private String fileType;
	private String fileDesct;
	private LocalDateTime fileDt;
	private String membId;
	private Long fileSize;
	
	public FileDTO() {}
	
	public FileDTO(String filePath, String fileNm, String fileType) {
		this.filePath = filePath;
		this.fileNm = fileNm;
		this.fileType = fileType;
		System.out.println(fileType);
	}
}

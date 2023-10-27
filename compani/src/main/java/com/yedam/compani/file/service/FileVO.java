package com.yedam.compani.file.service;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor // 생성자 
public class FileVO {
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
	
	private String type;
	private String keyword;
	
	@Builder
	public FileVO(String fileNm, String filePath, long fileSize) {
		this.fileNm = fileNm;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

}

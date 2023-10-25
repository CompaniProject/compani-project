package com.yedam.compani.issue.file.service;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 생성자 
public class IssueFileVO {
	private int issuFileNo; 					// 파일 번호 (PK)
	private String issuFileNm; 				// 원본 파일명
	private String issuFilePath; 			// 저장 파일명
	private int issuNo; 						// 이슈 게시글 번호 (FK)
	private long issuFileSize; 				// 파일 크기
	private Boolean deleteYn;
	private LocalDateTime issuFileDt;
	private LocalDateTime issuFileDelDt;
		
	@Builder
	public IssueFileVO(String issuFileNm, String issuFilePath, long issuFileSize) {
		this.issuFileNm = issuFileNm;
		this.issuFilePath = issuFilePath;
		this.issuFileSize = issuFileSize;
	}
	
	public void setIssuNo(final int issuNo) {  // 파일은 게시글이 생성(INSERT) 된 후에 처리되어야 함.
		this.issuNo = issuNo;
	}
}

package com.yedam.compani.issue.file.mapper;

import java.util.List;

import com.yedam.compani.issue.file.service.IssueFileVO;

public interface IssueFileMapper {	
	/**
	 *  파일 정보 등록
	 * @param issueFileVO - 파일 정보 리스트
	 */
	
	public void modalInsertIssueFile(List<IssueFileVO> files);
	
}

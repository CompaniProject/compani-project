package com.yedam.compani.issue.file.service;

import java.util.List;

public interface IssueFileService {

	public void modalInsertIssueFile(final int issuNo, final List<IssueFileVO> files);

	// 해당 이슈에 대한 등록된 모든 파일 조회
	public List<IssueFileVO> findAllFileByIssuNo(final int issuNo);

	public List<IssueFileVO> findAllByIds(final List<Integer> ids);

	public void deleteAllFileByIds(final List<Integer> ids);

	// 해당 파일 다운로드를 위한 단건 조회
	public IssueFileVO findFileById(final int issuFileNo);
	
	public int countFile(final int issuNo);
}

package com.yedam.compani.issue.file.mapper;

import java.util.List;

import com.yedam.compani.issue.file.service.IssueFileVO;

public interface IssueFileMapper {	
	/**
	 *  파일 정보 등록
	 * @param issueFileVO - 파일 정보 리스트
	 */
	
	public void modalInsertIssueFile(List<IssueFileVO> files);
	
	// 이슈 단건에 대한 모든 파일 조회
	public List<IssueFileVO> select(int issuNo);
		
	 /**
     * 파일 상세정보 조회
     * @param issuFileNo - PK
     * @return 파일 상세정보
     */
	public IssueFileVO findById(int issuFileNo);
	
	
	 /**
     * 파일 수 카운팅
     * @param issuNo - 게시글 번호 (FK)
     * @return 파일 수
     */
    int count(int issuNo);
}

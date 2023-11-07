package com.yedam.compani.issue.hashtag.service;

import java.util.List;

public interface IssueHashtagService {
	// 모달 해시태그 등록
	public void modalInsertIssueHashtag(final int issuNo, final List<String> hashtags);
	
	// 모달 해시태그 조회
	public List<IssueHashtagVO> findAllHashtagsByIssuNo(final int issuNo);
	
	// 모달 해시태그 수정
	public void modalEditIssueHashtag(final int issuNo, final List<String> hashtags);
	
	public void deleteHashtagbyId(final int issuNo);
	// 프로젝트 이슈 게시판에서 해시태그 출력.
	public List<IssueHashtagVO> selectHashtagByPrjtNo(final int prjtNo);
}

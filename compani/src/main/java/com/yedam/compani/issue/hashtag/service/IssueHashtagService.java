package com.yedam.compani.issue.hashtag.service;

import java.util.List;

public interface IssueHashtagService {
	// 모달 해시태그 등록
	public void modalInsertIssueHashtag(final int issuNo, final List<String> hashtags);
	
	// 모달 해시태그 조회
	public List<IssueHashtagVO> findAllHashtagsByIssuNo(final int issuNo);
}

package com.yedam.compani.issue.hashtag.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yedam.compani.issue.hashtag.mapper.IssueHashtagMapper;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueHashtagServiceImpl implements IssueHashtagService {	

	private final IssueHashtagMapper issueHashtagMapper;

	/**
	 * 해시태그 저장
	 *
	 * @param issuNo - 이슈글 번호 (FK), HtNm 리스트
	 *
	 */
	@Override
	public void modalInsertIssueHashtag(final int issuNo, final List<String> hashtags) {
		if(CollectionUtils.isEmpty(hashtags) ) {
			return;
		}
		issueHashtagMapper.insert(issuNo, hashtags);
	}
	
	/**
	 * 해시태그 조회
	 *
	 * @param issuNo - 이슈글 번호 (FK)
	 * @return 해시태그 리스트
	 */	
	@Override
	public List<IssueHashtagVO> findAllHashtagsByIssuNo(int issuNo) {
		return issueHashtagMapper.select(issuNo);
	}

}

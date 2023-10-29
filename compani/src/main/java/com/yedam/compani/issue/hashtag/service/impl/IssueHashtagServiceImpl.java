package com.yedam.compani.issue.hashtag.service.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * @param issuNo - 이슈글 번호 (FK), 해시태그 리스트
	 *
	 */
	@Override
	public void modalInsertIssueHashtag(final int issuNo, final List<IssueHashtagVO> hashtags) {
		 issueHashtagMapper.insert(hashtags);
	}

}

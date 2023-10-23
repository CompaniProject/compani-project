package com.yedam.compani.issue.hashtag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.issue.hashtag.mapper.IssueHashtagMapper;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

@Service
public class IssueHashtagServiceImpl implements IssueHashtagService {	
	@Autowired
	private IssueHashtagMapper hashtagMapper;
	
	@Override
	public int modalInsertIssueHashtag(IssueHashtagVO issueHashtagVO) {

		return hashtagMapper.modalInsertIssueHashtag(issueHashtagVO);
	}

}

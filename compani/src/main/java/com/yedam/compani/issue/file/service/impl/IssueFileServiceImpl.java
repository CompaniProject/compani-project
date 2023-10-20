package com.yedam.compani.issue.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.compani.issue.file.mapper.IssueFileMapper;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.file.service.IssueFileVO;

@Service
public class IssueFileServiceImpl implements IssueFileService {
	
	@Autowired
	private IssueFileMapper issueFileMapper;
	
	
	@Override
	public int modalInsertIssueFile(IssueFileVO issueFileVO) {
		
		return issueFileMapper.modalInsertIssueFile(issueFileVO);
	}

}

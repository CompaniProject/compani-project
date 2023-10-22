package com.yedam.compani.issue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yedam.compani.issue.mapper.IssueMapper;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

@Service
public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssueMapper issueMapper;
	
	@Override
	public Page<IssueVO> getIssueList(int pageNo, SearchDto search) {
		PageHelper.startPage(pageNo, 6);
		return issueMapper.findIssue(search);
	}

	@Override
	public int modalInsertIssue(final IssueVO issueVO) {
		issueMapper.modalInsertIssue(issueVO);
		
		return issueVO.getIssuNo();
	}

}

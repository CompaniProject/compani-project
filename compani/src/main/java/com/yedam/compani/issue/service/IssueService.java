package com.yedam.compani.issue.service;


import java.util.List;

import com.github.pagehelper.Page;
import com.yedam.compani.paging.SearchDto;

public interface IssueService {
	// 이슈 전체 조회(검색)
	public Page<IssueVO> getIssueList(int pageNo, SearchDto search);
	
	// 이슈 단건 조회
	public IssueVO getIssueInfo(IssueVO issueVO);
	
	// 모달 이슈 등록
	public int modalInsertIssue(final IssueVO issueVO);
	
	public List<IssueVO> getIssueList();
	
}

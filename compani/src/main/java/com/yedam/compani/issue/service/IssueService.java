package com.yedam.compani.issue.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yedam.compani.paging.SearchDto;

public interface IssueService {
	// 이슈 전체 조회(검색)
	public Page<IssueVO> getIssueList(int pageNo, SearchDto search);
	
	//메인홈 이슈 리스트 조회
	public List<IssueVO> getIssueList();
	
}

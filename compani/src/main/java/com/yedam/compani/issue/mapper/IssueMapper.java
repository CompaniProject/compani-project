package com.yedam.compani.issue.mapper;


import java.util.List;

import com.github.pagehelper.Page;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

public interface IssueMapper {
	Page<IssueVO> findIssue(SearchDto search); 
	public List<IssueVO> getIssueList();
}

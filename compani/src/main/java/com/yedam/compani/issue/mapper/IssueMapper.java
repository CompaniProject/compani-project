package com.yedam.compani.issue.mapper;


import com.github.pagehelper.Page;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

public interface IssueMapper {
	Page<IssueVO> findIssue(SearchDto search); 
}

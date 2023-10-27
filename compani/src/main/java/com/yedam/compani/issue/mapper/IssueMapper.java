package com.yedam.compani.issue.mapper;



import java.util.List;

import com.github.pagehelper.Page;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.paging.SearchDto;

public interface IssueMapper {
	Page<IssueVO> findIssue(SearchDto search);
	
	// 모달 창에서 이슈 등록
	public int modalInsertIssue(IssueVO issueVO);
	
	// 전체 이슈 목록
	public List<IssueVO> getIssueList();
	
	// 이슈 단건 조회
	public IssueVO selectIssueInfo(IssueVO issueVO);
	
	// 프로젝트 이슈 피드백
	public List<IssueVO> selectProjectFeedbackIssueList();
}

package com.yedam.compani.issue.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yedam.compani.member.service.MemberVO;

public interface IssueService {
	// 이슈 전체 조회(검색)
	public Page<IssueVO> getIssueList(int pageNo, String search, String keyword, final int bussNo);
	
	public Page<IssueVO> getProjectIssueList(int pageNo, String search, String keyword, final int prjtNo);
	
	public Page<IssueVO> getCompanyIssueList(int pageNo, String search, String keyword, final String coCd);
	
	// 이슈 단건 조회
	public IssueVO findIssueById(final int issuNo);

	// 모달 이슈 등록
	public int modalInsertIssue(final IssueVO issueVO);

	public List<IssueVO> getIssueList(MemberVO memberVO);

	// 이슈 수정
	public int updateIssue(final IssueVO params);
	
	// 이슈 삭제
	public int deleteIssue(final int issuNo);

	// 프로젝트 이슈 피드백
	public List<IssueVO> getProjectFeedbackIssueList();
}

package com.yedam.compani.issue.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yedam.compani.issue.mapper.IssueMapper;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.service.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

	private final IssueMapper issueMapper;

	/**
	 * 이슈 리스트 조회
	 * 
	 * @param pageNo, search conditions
	 * @return list & pagination information
	 */
	@Override
	public Page<IssueVO> getIssueList(int pageNo, String search, String keyword, int bussNo, String filterTypeM) {
		PageHelper.startPage(pageNo, 6);
		return issueMapper.findIssue(search, keyword, bussNo, filterTypeM);
	}

	/**
	 * 프로젝트 내 이슈 리스트 조회
	 * 
	 * @param pageNo, search conditions, prjtNo
	 * @return list & pagination information
	 */
	@Override
	public Page<IssueVO> getProjectIssueList(int pageNo, String search, String keyword, int prjtNo, String filterType) {
		PageHelper.startPage(pageNo, 10);
		return issueMapper.findProjectIssue(search, keyword, prjtNo, filterType);
	}
	/**
	 * 회사 내 이슈 리스트 조회
	 * 
	 * @param pageNo, search conditions, coCd
	 * @return list & pagination information
	 */
	@Override
	public Page<IssueVO> getCompanyIssueList(int pageNo, String search, String keyword, String coCd, String filterType) {
		PageHelper.startPage(pageNo, 10);
		return issueMapper.findCompanyIssue(search, keyword, coCd, filterType);
	}
	
	/**
	 * 이슈 상세정보 조회
	 * 
	 * @param issuNo - PK
	 * @return 이슈 상세정보
	 */
	@Override
	public IssueVO findIssueById(final int issuNo) {
		return issueMapper.findById(issuNo);
	}

	/**
	 * 모달 이슈 저장
	 * 
	 * @param params - 이슈 정보
	 * @return Generated PK
	 */
	@Override
	public int modalInsertIssue(final IssueVO issueVO) {
		issueMapper.modalInsertIssue(issueVO);

		return issueVO.getIssuNo();
	}

	@Override
	public List<IssueVO> getIssueList(MemberVO memberVO) {

		return issueMapper.getIssueList(memberVO);
	}

	/**
	 * 게시글 수정
	 * 
	 * @param params - 이슈 정보
	 * @return PK
	 */
	@Override
	public int updateIssue(IssueVO params) {
		issueMapper.update(params);
		return params.getIssuNo();
	}
	
	// 프로젝트 이슈 피드백
	@Override
	public List<IssueVO> getProjectFeedbackIssueList(int prjtNo) {
		return issueMapper.selectProjectFeedbackIssueList(prjtNo);
	}


	/**
	 * 게시글 삭제
	 * 
	 * @param PK
	 * @return PK
	 */
	@Override
	public int deleteIssue(int issuNo) {
		issueMapper.deleteById(issuNo);
		return issuNo;
	}

}

package com.yedam.compani.issue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yedam.compani.issue.service.IssueVO;
import com.yedam.compani.member.service.MemberVO;

public interface IssueMapper {
	
	Page<IssueVO> findIssue(@Param("searchBI")String search, @Param("keyword") String keyword, @Param("bussNo") int bussNo, @Param("filterTypeM")String filterType);
	
	Page<IssueVO> findProjectIssue(@Param("search")String search, @Param("keyword") String keyword, @Param("prjtNo") int prjtNo, @Param("filterType")String filterType);
	
	Page<IssueVO> findCompanyIssue(@Param("search")String search, @Param("keyword") String keyword, @Param("coCd") String coCd, @Param("filterType")String filterType);
	
	/**
	 * 모달 이슈 저장
	 * 
	 * @param params - 이슈 정보
	 */
	int modalInsertIssue(IssueVO issueVO);

	/**
	 * 모달 이슈 리스트 조회
	 * 
	 * @return 이슈 리스트
	 */
	List<IssueVO> getIssueList(MemberVO memberVO);

	/**
	 * 이슈 상세정보 조회
	 * 
	 * @param params - 이슈 정보
	 * @return 이슈 상세정보
	 */
	IssueVO findById(int issuNo);

	/**
	 * 이슈 수정
	 * 
	 * @param params - 게시글 정보
	 * @return PK
	 */
	void update(IssueVO params);
	
	/**
     * 이슈 삭제
     *
     * @param id - PK
     */
    void deleteById(int issuNo);

	// 프로젝트 이슈 피드백
	public List<IssueVO> selectProjectFeedbackIssueList(int prjtNo);
}

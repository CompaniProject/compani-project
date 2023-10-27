package com.yedam.compani.issue.reply.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.compani.issue.reply.mapper.IssueReplyMapper;
import com.yedam.compani.issue.reply.service.IssueReplyService;
import com.yedam.compani.issue.reply.service.IssueReplyVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueReplyServiceImpl implements IssueReplyService {
	private final IssueReplyMapper issueReplyMapper;

	/**
	 * 댓글 저장
	 * 
	 * @param params - 댓글 정보
	 * @return Generated PK
	 */

	@Override
	public int saveIssueReply(final IssueReplyVO params) {
		issueReplyMapper.save(params);
		return params.getIssuRplyNo();
	}

	/**
	 * 댓글 상세정보 조회
	 * 
	 * @param issuRplyNo - PK
	 * @return 댓글 상세정보
	 */

	@Override
	public IssueReplyVO findReplyById(final int issuRplyNo) {
		return issueReplyMapper.findById(issuRplyNo);
	}

	/**
	 * 댓글 수정
	 * 
	 * @param params - 댓글 정보
	 * @return PK
	 */

	@Override
	public int updateReply(final IssueReplyVO params) {
		issueReplyMapper.update(params);
		return params.getIssuRplyNo();
	}

	/**
	 * 댓글 삭제
	 * 
	 * @param issuRplyNo - PK
	 * @return PK
	 */

	@Override
	public int deleteReply(final int issuRplyNo) {
		issueReplyMapper.deleteById(issuRplyNo);
		return issuRplyNo;
	}

	/**
	 * 댓글 리스트 조회
	 * 
	 * @param issuNo - 게시글 번호 (FK)
	 * @return 특정 게시글에 등록된 댓글 리스트
	 */

	@Override
	public List<IssueReplyVO> findAllReply(final int issuNo) {
		return issueReplyMapper.findAll(issuNo);
	}
	
	/**
	 * 댓글 수 카운트
	 * 
	 * @param issuNo - 게시글 번호 (FK)
	 * @return 해당 게시글에 달린 댓글 수
	 */
	
	@Override
	public int countReply(int issuNo) {
		return issueReplyMapper.count(issuNo);
	}

}

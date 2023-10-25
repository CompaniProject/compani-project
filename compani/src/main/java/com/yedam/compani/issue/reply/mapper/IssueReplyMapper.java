package com.yedam.compani.issue.reply.mapper;

import java.util.List;

import com.yedam.compani.issue.reply.service.IssueReplyVO;

public interface IssueReplyMapper {
	/**
     * 댓글 저장
     * @param params - 댓글 정보
     */
    void save(IssueReplyVO params);

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    IssueReplyVO findById(int issuRplyNo);

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     */
    void update(IssueReplyVO params);

    /**
     * 댓글 삭제
     * @param id - PK
     */
    void deleteById(int issuRplyNo);

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 댓글 리스트
     */
    List<IssueReplyVO> findAll(int issuNo);

    /**
     * 댓글 수 카운팅
     * @param postId - 게시글 번호 (FK)
     * @return 댓글 수
     */
    int count(int issuNo);
}

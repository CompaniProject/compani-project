package com.yedam.compani.issue.hashtag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

public interface IssueHashtagMapper {

	/**
	 * 해시태그 정보 등록
	 *
	 * @param hashtags - 해시태그 리스트
	 */
	void insert(@Param("issuNo") int issuNo, @Param("hashtags") List<String> hashtags);
    
	/**
     * 해시태그 리스트 조회
     *
     * @param issuNo - 이슈 번호 (FK)
     * @return 해시태그 리스트
     */
	List<IssueHashtagVO> select(int issuNo);
}

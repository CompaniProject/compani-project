package com.yedam.compani.issue.hashtag.mapper;

import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

import java.util.List;

public interface IssueHashtagMapper {

	/**
	 * 해시태그 정보 등록
	 *
	 * @param hashtags - 해시태그 리스트
	 */
	void insert(List<IssueHashtagVO> hashtags);
	
}

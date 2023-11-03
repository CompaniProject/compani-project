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
	
	/**
	 * 해시태그 정보 수정 ( 해당 이슈에 있는 해시태그를 모두 삭제 후, 다시 등록하는 것 )
	 *
	 * @param hashtags - 해시태그 리스트
	 */	
	void edit(@Param("issuNo") int issuNo, @Param("hashtags") List<String> hashtags);
	
	/**
	 * 해시태그 정보 수정 ( 해당 이슈에 있는 해시태그를 모두 삭제 후, 다시 등록하는 것 )
	 *
	 * @param hashtags - 해시태그 리스트
	 */	
	void delete(int issuNo);
}

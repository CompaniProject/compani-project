package com.yedam.compani.issue.hashtag.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yedam.compani.issue.hashtag.mapper.IssueHashtagMapper;
import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueHashtagServiceImpl implements IssueHashtagService {	

	private final IssueHashtagMapper issueHashtagMapper;

	/**
	 * 해시태그 저장
	 *
	 * @param issuNo - 이슈글 번호 (FK), HtNm 리스트
	 *
	 */
	@Override
	public void modalInsertIssueHashtag(final int issuNo, final List<String> hashtags) {
		if(CollectionUtils.isEmpty(hashtags) ) {
			return;
		}
		issueHashtagMapper.insert(issuNo, hashtags);
	}
	
	/**
	 * 해시태그 조회
	 *
	 * @param issuNo - 이슈글 번호 (FK)
	 * @return 해시태그 리스트
	 */	
	@Override
	public List<IssueHashtagVO> findAllHashtagsByIssuNo(final int issuNo) {
		return issueHashtagMapper.select(issuNo);
	}
	
	/**
	 * 해시태그 수정 ( 이슈에 해당하는 모든 해시태그 db에서 삭제 후 새로 등록)
	 *
	 * @param issuNo - 이슈글 번호 (FK), HtNm 리스트
	 *
	 */
	@Override
	public void modalEditIssueHashtag(final int issuNo, List<String> hashtags) {
		if(CollectionUtils.isEmpty(hashtags)) {
			return ;
		}
		issueHashtagMapper.edit(issuNo, hashtags);
		
	}

	@Override
	public void deleteHashtagbyId(final int issuNo) {
		issueHashtagMapper.delete(issuNo);		
	}

	/**
	 * 프로젝트 이슈 게시판 내 해시태그 조회
	 *
	 * @param prjtNo - 프로젝트 번호 (FK)
	 * @return 해시태그 리스트
	 */
	@Override
	public List<IssueHashtagVO> selectHashtagByPrjtNo(final int prjtNo) {
			return issueHashtagMapper.selectHashtagByPrjtNo(prjtNo);
	}

	/**
	 * 회사 이슈 게시판 내 해시태그 조회
	 *
	 * @param coCd - 회사 코드 (FK)
	 * @return 해시태그 리스트
	 */
	@Override
	public List<IssueHashtagVO> selectHashtagByCoCd(String coCd) {
		return issueHashtagMapper.selectHashtagByCoCd(coCd);
	}
}

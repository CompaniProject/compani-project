package com.yedam.compani.issue.hashtag.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

import lombok.RequiredArgsConstructor;

/*
 * 작성자: 권오준
 * 작성일자: 2023/11/10
 * 내용: 이슈 해시태그 조회
 */
@RestController
@RequiredArgsConstructor
public class IssueHashtagController {
	
	private final IssueHashtagService issueHashtagService;
	
	// 해시태그 리스트 조회
	@GetMapping("/issues/{issuNo}/hashtags")
	public List<IssueHashtagVO> findAllHashtagByIssuNo(@PathVariable final int issuNo) {
		return issueHashtagService.findAllHashtagsByIssuNo(issuNo);
	}
	
	// 프로젝트 이슈 게시판 내 해시태그 리스트 조회
	@GetMapping("/project/{prjtNo}/hashtags")
	public List<IssueHashtagVO> findAllHashtagByPrjtNo(@PathVariable final int prjtNo) {
		return issueHashtagService.selectHashtagByPrjtNo(prjtNo);
	}
	
	// 회사 이슈 게시판 내 해시태그 리스트 조회
	@GetMapping("/company/{coCd}/hashtags")
	public List<IssueHashtagVO> findAllHashtagByCoCd(@PathVariable final String coCd) {
		return issueHashtagService.selectHashtagByCoCd(coCd);
	}
}

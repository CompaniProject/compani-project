package com.yedam.compani.issue.hashtag.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.compani.issue.hashtag.service.IssueHashtagService;
import com.yedam.compani.issue.hashtag.service.IssueHashtagVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IssueHashtagController {
	
	private final IssueHashtagService issueHashtagService;
	
	// 해시태그 리스트 조회
	@GetMapping("/issues/{issuNo}/hashtags")
	public List<IssueHashtagVO> findAllHashtagByIssuNo(@PathVariable final int issuNo) {
		return issueHashtagService.findAllHashtagsByIssuNo(issuNo);
	}
}

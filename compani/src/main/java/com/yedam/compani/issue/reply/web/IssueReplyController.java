package com.yedam.compani.issue.reply.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.compani.issue.reply.service.IssueReplyService;
import com.yedam.compani.issue.reply.service.IssueReplyVO;

import lombok.RequiredArgsConstructor;

/*
 * 작성자: 권오준
 * 작성일자: 2023/11/10
 * 내용: 이슈 내 댓글 등록, 조회, 수정, 삭제
 */
@RestController //컨트롤러의 모든 메서드에 @ResponseBody가 적용되며, 응답으로 페이지가 아닌 리턴 타입에 해당하는 데이터(객체) 자체를 리턴.
@RequiredArgsConstructor
public class IssueReplyController {
	
	private final IssueReplyService issueReplyService;
	
	// 신규 댓글 생성 -> 새로운 댓글을 생성한 후 생성된 댓글의 상세정보(응답 객체)를 리턴
	@PostMapping("/issues/{issuNo}/reply") // @PathVariable -> Rest api 에서 리소스를 표현하는데 사용. 해당 어노테이션을 이용하면 URI에서 템플릿 형태로 파라미터를 전달받을 수 있음.
	public IssueReplyVO saveIssueReply(@PathVariable final int issuNo, @RequestBody final IssueReplyVO params) {
			int id = issueReplyService.saveIssueReply(params);
			return issueReplyService.findReplyById(id);
	}
	
	// 댓글 리스트 조회
	@GetMapping("/issues/{issuNo}/reply")
	public List<IssueReplyVO> findAllReply(@PathVariable final int issuNo) {
		return issueReplyService.findAllReply(issuNo);
	}
	
	// 댓글 상세정보 조회
	@GetMapping("/issues/{issuNo}/reply/{issuRplyNo}")
	public IssueReplyVO findReplyById(@PathVariable final int issuNo, @PathVariable final int issuRplyNo) {
		return issueReplyService.findReplyById(issuRplyNo);
	}
	
	// 기존 댓글 수정
	@PutMapping("/issues/{issuNo}/reply/{issuRplyNo}")
	public IssueReplyVO updateReply(@PathVariable final int issuNo, @PathVariable final int issuRplyNo, @RequestBody final IssueReplyVO params) {
		issueReplyService.updateReply(params);
		return issueReplyService.findReplyById(issuRplyNo);
	}
	
	// 댓글 삭제
	@DeleteMapping("issues/{issuNo}/reply/{issuRplyNo}")
	public int deleteReply(@PathVariable final int issuNo, @PathVariable final int issuRplyNo) {
		return issueReplyService.deleteReply(issuRplyNo);
	}
	
	// 댓글 수 카운트
	@GetMapping("issues/{issuNo}/reply/count")
	public int countReply(@PathVariable final int issuNo) {
		return issueReplyService.countReply(issuNo);
	}
}

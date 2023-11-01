package com.yedam.compani.issue.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.compani.issue.file.service.IssueFileVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yedam.compani.issue.file.service.IssueFileService;
import com.yedam.compani.issue.service.IssueService;
import com.yedam.compani.issue.service.IssueVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyIssueController {

	private final IssueService issueService;
	private final IssueFileService issueFileService;
	private final com.yedam.compani.config.FileUtils fileUtils;

	// 회사 이슈 게시판에서 이슈 리스트 나오기
	@GetMapping("/company/issues/{coCd}")
	public String companyIssueList(@PathVariable String coCd, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getCompanyIssueList(pageNum, search, keyword, coCd), 8);
		Page<IssueVO> vo = issueService.getCompanyIssueList(pageNum, search, keyword, coCd);

		model.addAttribute("companyIssue", issues);
		model.addAttribute("companyIssueList", vo);

		return "company/company-issue";
	}

	// 회사 내 이슈 리스트 조회 (Ajax)
	@GetMapping("/company/aissues/{coCd}")
	@ResponseBody
	public Map<String, Object> companyIssue(@PathVariable String coCd, String search, String keyword,
			@RequestParam(required = false, defaultValue = "1") int pageNum) {
		PageInfo<IssueVO> issues = new PageInfo<>(issueService.getCompanyIssueList(pageNum, search, keyword, coCd), 8);
		Page<IssueVO> vo = issueService.getCompanyIssueList(pageNum, search, keyword, coCd);
		Map<String, Object> map = new HashMap<>();

		map.put("cissue", issues);
		map.put("cissueList", vo);

		return map;
	}

	// 회사 내 이슈 단건 조회 + 해당 이슈에 대한 모든 파일 조회
	@GetMapping("/company/issues/{coCd}/{issuNo}")
	public String projectIssueSelect(@PathVariable final String coCd, @PathVariable final int issuNo, Model model) {

		IssueVO vo = issueService.findIssueById(issuNo);
		model.addAttribute("issueInfo", vo);

		List<IssueFileVO> list = issueFileService.findAllFileByIssuNo(issuNo);
		model.addAttribute("issueFile", list);

		return "company/company-issue-info";
	}

	// 회사 이슈 게시판 내 이슈 삭제
	@PostMapping("/company/issues/del")
	@ResponseBody
	public void projectIssueDelete(@RequestParam final int issuNo) {
		issueService.deleteIssue(issuNo);
	}
}
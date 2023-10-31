package com.yedam.compani.issue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		Page<IssueVO> vo = issueService.getProjectIssueList(pageNum, search, keyword, pageNum);
		Map<String, Object> map = new HashMap<>();

		map.put("cissue", issues);
		map.put("cissueList", vo);

		return map;
	}
}
